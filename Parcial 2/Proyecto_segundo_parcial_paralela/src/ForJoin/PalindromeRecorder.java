import java.util.concurrent.atomic.AtomicReference;

public class PalindromeRecorder {

    private final AtomicReference<StringBuilder> record = new AtomicReference<>(new StringBuilder());

    public void record(String word) {
        StringBuilder oldRecord;
        StringBuilder newRecord;
        do {
            oldRecord = record.get();
            newRecord = new StringBuilder(oldRecord);
            newRecord.append(word).append(", ");
        } while (!record.compareAndSet(oldRecord, newRecord));
    }

    public String getRecord() {
        StringBuilder builder = record.get();
        int length = builder.length();
        return (length > 2) ? builder.substring(0, length - 2) : "";
    }

}
