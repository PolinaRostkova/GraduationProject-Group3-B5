package io.loop.utilities;

import java.sql.*;
import java.util.*;

public class DB_Utility {

    // declaring at class level so all methods can access
    private static Connection con ;
    private static Statement stm ;
    private static ResultSet rs ;
    private static ResultSetMetaData rsmd;
    /**
     *
     * @param query
     * @return returns query result in a list of lists where outer list represents
     *         collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(rs.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    /**
     *
     * @param query
     * @return returns a single cell value. If the results in multiple rows and/or
     *         columns of data, only first column of the first row will be returned.
     *         The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }
    /**
     *
     * @param query
     * @return returns a list of Strings which represent a row of data. If the query
     *         results in multiple rows and/or columns of data, only first row will
     *         be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }
    /**
     *
     * @param query
     * @return returns a map which represent a row of data where key is the column
     *         name. If the query results in multiple rows and/or columns of data,
     *         only first row will be returned. The rest of the data will be ignored
     */

    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    private static void executeQuery(String query) {
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            rs = stm.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     *
     * @param query
     * @return returns query result in a list of maps where the list represents
     *         collection of rows and a map represents represent a single row with
     *         key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = rs.getMetaData();
            while (rs.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }
    /**
     * Create Connection by jdbc url and username , password provided
     * @param url  jdbc url for any database
     * @param username username for database
     * @param password password for database
     */
    public static Connection createConnection(String url , String username, String password){
        try {
            con = DriverManager.getConnection(url, username, password) ;
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (Exception e) {
            System.out.println("CONNECTION HAS FAILED " + e.getMessage() );
        }
        return con;
    }


    /**
     * Create connection method , just checking one connection successful or not
     */
    public static Connection createConnection(){
        // PLEASE, MAKE SURE YOU UPDATE THE IP ADDRESS TO THE ONE YOU HAVE CURRENTLY
        String url = "jdbc:oracle:thin:@98.80.65.248:1521:XE" ;
        //String url = ConfigurationReader.getProperty("hr.db.url"); // Since we have added config.properties and ConfigReader, I can get directly from there as well
        String username =  "hr";
        String password =  "hr";

       con = createConnection(url, username, password);
       return con;
    }

    public static Connection docuportCreateConnection(){
        // PLEASE, MAKE SURE YOU UPDATE THE IP ADDRESS TO THE ONE YOU HAVE CURRENTLY
        String url = ConfigurationReader.getProperty("docuport.db.url");
        //String url = ConfigurationReader.getProperty("hr.db.url"); // Since we have added config.properties and ConfigReader, I can get directly from there as well
        String username = ConfigurationReader.getProperty("docuport.db.username");
        String password =  ConfigurationReader.getProperty("docuport.db.password");

       con =  createConnection(url, username, password);
       return con;
    }


    /**
     * Run the sql query provided and return ResultSet object
     * @param sql the query to run
     * @return ResultSet object  that contains data
     */
    public static ResultSet runQuery(String sql){   // DB_Util.runQuery("SELECT * FROM EMPLOYEES");
        try {
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql); // setting the value of ResultSet object
            rsmd = rs.getMetaData() ;  // setting the value of ResultSetMetaData for reuse
        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE RUNNING QUERY "+ e.getMessage() );
        }
        return rs ;
    }

    /**
     * destroy method to clean up all the resources after being used
     */
    public static void destroy(){
        // WE HAVE TO CHECK IF WE HAVE THE VALID OBJECT FIRST BEFORE CLOSING THE RESOURCE
        // BECAUSE WE CAN NOT TAKE ACTION ON AN OBJECT THAT DOES NOT EXIST
        try {
            if( rs!=null)  rs.close();
            if( stm!=null)  stm.close();
            if( con!=null)  con.close();
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE CLOSING RESOURCES " + e.getMessage() );
        }
    }

    /**
     * This method will reset the cursor to before first location
     */
    public static void resetCursor () {
        try {
            rs.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * find out the row count
     * @return row count of this ResultSet
     */
    public static int getRowCount(){

        int rowCount = 0 ;
        try {
            rs.last() ;
            rowCount = rs.getRow() ; // .getRow() -- > will return the number of the row you are currently at
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE GETTING ROW COUNT " + e.getMessage() );
        }finally {
            resetCursor();
        }

        return rowCount ;
    }


    /**
     * find out the column count
     * @return column count of this ResultSet
     */
    public static int getColumnCount(){
        int columnCount = 0 ;
        try {
            columnCount = rsmd.getColumnCount();
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE GETTING COLUMN COUNT " + e.getMessage() );
        }
        return columnCount ;
    }



    /**
     * // Get all the Column names into a list object
     * @return  List<String>
     */
    public static List<String> getAllColumnNamesAsList(){
        List<String> columnNameLst = new ArrayList<>();
        try {
            for (int colIndex = 1; colIndex <= getColumnCount() ; colIndex++) {
                String columnName =  rsmd.getColumnName(colIndex) ;
                columnNameLst.add(columnName) ;
            }
        }catch (Exception e){
            System.out.println("ERROR OCCURRED WHILE getAllColumnNamesAsList "+ e.getMessage());
        }
        return columnNameLst ;
    }

    // get entire row of data according to row number

    /**
     * Getting entire row of data in a List of String
     * @param rowNum row number to get as a list
     * @return row data as List of String
     */
    public static List<String> getRowDataAsList( int rowNum ){
        List<String> rowDataAsLst = new ArrayList<>();
        int colCount =  getColumnCount() ;
        try {
            rs.absolute( rowNum );
            for (int colIndex = 1; colIndex <= colCount ; colIndex++) {
                String cellValue =  rs.getString( colIndex ) ;
                rowDataAsLst.add(   cellValue  ) ;
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getRowDataAsList " + e.getMessage() );
        } finally {
            resetCursor();
        }

        return rowDataAsLst ;
    }



    /**
     * getting the cell value according to row num and column index
     * @param rowNum  row number to get the data from
     * @param columnIndex column number to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum , int columnIndex) {

        String cellValue = "" ;

        try {
            rs.absolute(rowNum) ;
            cellValue = rs.getString(columnIndex ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }

    /**
     * getting the cell value according to row num and column name
     * @param rowNum  row number to get the data from
     * @param columnName column Name to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum ,String columnName){

        String cellValue = "" ;

        try {
            rs.absolute(rowNum) ;
            cellValue = rs.getString( columnName ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }


    /**
     * Get First Cell Value at First row First Column
     */
    public static String getFirstRowFirstColumn(){
        return getCellValue(1,1) ;
    }


    /**
     * getting entire column data as list according to column number
     * @param columnNum column number to get all data
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(int columnNum){

        List<String> columnDataLst = new ArrayList<>();

        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while( rs.next() ){

                String cellValue = rs.getString(columnNum) ;
                columnDataLst.add(cellValue) ;
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return columnDataLst ;

    }

    /**
     * getting entire column data as list according to column Name
     * @param columnName column name to get all data
     * @return List object that contains all rows of that column
     */
    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataLst = new ArrayList<>();

        try {
            rs.beforeFirst(); // make sure the cursor is at before first location
            while( rs.next() ){

                String cellValue = rs.getString(columnName) ;
                columnDataLst.add(cellValue) ;
            }

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return columnDataLst ;

    }


    /**
     * display all data from the ResultSet Object
     */
    public static void  displayAllData(){

        int columnCount = getColumnCount() ;
        resetCursor();
        try{

            while(rs.next()){

                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {

                    System.out.print( rs.getString(colIndex) + "\t" );
                    //System.out.printf("%-25s", rs.getString(colIndex));
                }
                System.out.println();

            }

        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE displayAllData " + e.getMessage() );
        }finally {
            resetCursor();
        }

    }

    /**
     * Save entire row data as Map<String,String>
     * @param rowNum row number
     * @return Map object that contains key value pair
     *      key     : column name
     *      value   : cell value
     */
    public static Map<String,String> getRowMap(int rowNum){

        Map<String,String> rowMap = new LinkedHashMap<>();
        int columnCount = getColumnCount() ;

        try{

            rs.absolute(rowNum) ;

            for (int colIndex = 1; colIndex <= columnCount ; colIndex++) {
                String columnName = rsmd.getColumnName(colIndex) ;
                String cellValue  = rs.getString(colIndex) ;
                rowMap.put(columnName, cellValue) ;
            }

        }catch(Exception e){
            System.out.println("ERROR OCCURRED WHILE getRowMap " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return rowMap ;
    }
    /**
     * We know how to store one row as map object
     * Now Store All rows as List of Map object
     * @return List of Map object that contain each row data as Map<String,String>
     */
    public static List<Map<String,String>> getAllRowAsListOfMap() {

        List<Map<String, String>> allRowLstOfMap = new LinkedList<>();
        int rowCount = getRowCount();
        // move from first row till last row
        // get each row as map object and add it to the list

        for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {

            Map<String, String> rowMap = getRowMap(rowIndex);
            allRowLstOfMap.add(rowMap);

        }
        resetCursor();

        return allRowLstOfMap;

    }
}
