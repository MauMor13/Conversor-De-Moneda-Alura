package services;
import models.Conversion;
import java.util.List;

public interface FileManagementService {
    List<Conversion> readFileHistory();
    void writeFileHistory(List<Conversion> conversionArrayList);
}
