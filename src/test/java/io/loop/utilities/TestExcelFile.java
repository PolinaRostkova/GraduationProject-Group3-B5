package io.loop.utilities;

public class TestExcelFile {
    public static void main(String[] args) {
        ExcelUtils excelUtils = new ExcelUtils("/Users/Polina/IdeaProjects/cucumber-project-B5/src/test/resources/Book1.xlsx", "Sheet1");

        System.out.println(excelUtils.getCellData(0, 0));
        System.out.println(excelUtils.getCellData(0, 1));

        excelUtils.setCellData("Owner", 0, 2);
        excelUtils.setCellData("Feyruz", 1, 2);



    }
}
