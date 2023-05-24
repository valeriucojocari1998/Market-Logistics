package helpers;

import enums.ClothingSize;
import enums.ProductStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EnumHelpers {
    public static ProductStatus parseProductStatus(String statusString) {
        try {
            return ProductStatus.valueOf(statusString);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status value");
            return null;
        }
    }

    public static ClothingSize parseClothingSize(String sizeString) {
        try {
            return ClothingSize.valueOf(sizeString);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid clothing size value");
            return null;
        }
    }

    public static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
            return null;
        }
    }
}
