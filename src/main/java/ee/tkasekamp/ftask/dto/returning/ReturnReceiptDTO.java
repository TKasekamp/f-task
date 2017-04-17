package ee.tkasekamp.ftask.dto.returning;

import java.util.List;

public class ReturnReceiptDTO {
    private long total;
    private List<ReturnReceiptItemDTO> items;

    public ReturnReceiptDTO(long total, List<ReturnReceiptItemDTO> items) {
        this.total = total;
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public List<ReturnReceiptItemDTO> getItems() {
        return items;
    }

}
