package Calculadora;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Servidor {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService pool = Executors.newFixedThreadPool(2);
    int cont = 0;

    public Servidor() throws IOException {
        serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor iniciado, esperando por clientes...");
    }

    public void execute() {
        try {
            while (true) {
                Socket clientSocket1 = serverSocket.accept();
                System.out.println("Cliente 1 conectado");

                Socket clientSocket2 = serverSocket.accept();
                System.out.println("Cliente 2 conectado");

                ClientHandler clientHandler1 = new ClientHandler(clientSocket1, 1);
                ClientHandler clientHandler2 = new ClientHandler(clientSocket2, 2);

                // Crear una nueva instancia de Session para cada par de clientes
                Session session = new Session(clientHandler1, clientHandler2);
                clientHandler1.setSession(session);
                clientHandler2.setSession(session);

                pool.execute(clientHandler1);
                pool.execute(clientHandler2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Servidor server = new Servidor();
        server.execute();
    }
    
    // Clase para manejar una sesión entre dos clientes
    public static class Session {
        ClientHandler client1;
        ClientHandler client2;
        double[] digits = new double[2];
        boolean[] digitsSet = new boolean[2];
        String[] operations = new String[2];

        public Session(ClientHandler client1, ClientHandler client2) {
            this.client1 = client1;
            this.client2 = client2;
        }

        public synchronized boolean setDigit(int clientNumber, double digit) {
            if (!digitsSet[clientNumber - 1]) {
                digits[clientNumber - 1] = digit;
                digitsSet[clientNumber - 1] = true;
                return true;
            }
            return false;
        }

        public synchronized boolean setOperation(int clientNumber, String operation) {
            if (operations[clientNumber - 1] == null) {
                operations[clientNumber - 1] = operation;
                return true;
            }
            return false;
        }

        public synchronized boolean isReadyForCalculation() {
            return digitsSet[0] && digitsSet[1] && operations[0] != null && operations[1] != null;
        }

        public synchronized double calculateResultForClient(int clientNumber) {
            // Determinar la operación y calcular el resultado
            String operation = operations[clientNumber - 1];
            double a = digits[0]; // Dígito del Cliente 1
            double b = digits[1]; // Dígito del Cliente 2
            metodos metodos = new metodos();
            double result = 0;
            System.out.println(operation);

            switch (operation) {
                case "+":
                    result = metodos.suma(a, b);
                    break;
                case "-":
                    result = metodos.resta(a, b);
                    break;
                case "*":
                    result = metodos.multiplicacion(a, b);
                    break;
                case "/":
                    result = metodos.division(a, b);
                    break;
                case "^":
                    result = metodos.potencia(a, b);
                    break;
                case "√":
                    result = metodos.raiz(clientNumber == 1 ? a : b);
                    break;
                case "%":
                    result = metodos.modulo(a, b);
                    break;
                case "C":
                    resetSession();
                    break;
            }
            return result;
        }
        
        public synchronized void resetSession() {
            System.out.println("Limpieza");
            digitsSet[0] = false;
            digitsSet[1] = false;
            operations[0] = null;
            operations[1] = null;
        }

    }

    private static class ClientHandler implements Runnable {
        public Socket clientSocket;
        private DataInputStream input;
        private DataOutputStream output;
        public int clientNumber;
        public Session session;

        public ClientHandler(Socket clientSocket, int clientNumber) throws IOException {
            this.clientSocket = clientSocket;
            this.clientNumber = clientNumber;
            input = new DataInputStream(clientSocket.getInputStream());
            output = new DataOutputStream(clientSocket.getOutputStream());
        }

        public void setSession(Session session) {
            this.session = session;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Leer dígito y operación de este cliente
                    double digit = input.readDouble();
                    String operation = input.readUTF();

                    // Establecer el dígito y la operación en la sesión
                    if (session.setDigit(clientNumber, digit) && session.setOperation(clientNumber, operation)) {
                        System.out.println("Cliente " + clientNumber + " envió dígito y operación: " + digit + " " + operation);
                    }

                    // Verificar si estamos listos para calcular el resultado
                    if (session.isReadyForCalculation()) {
                        double result = session.calculateResultForClient(clientNumber);
                        output.writeDouble(result);
                        System.out.println("Resultado enviado a Cliente " + clientNumber + ": " + result);
                    }
                }
            } catch (IOException e) {
                System.out.println("Cliente " + clientNumber + " desconectado.");
            } finally {
                try {
                    input.close();
                    output.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
