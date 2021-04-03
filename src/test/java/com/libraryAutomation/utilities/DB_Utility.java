package com.libraryAutomation.utilities;

import java.sql.*;
import java.util.*;

public class DB_Utility {
    private static Connection con;
    private static ResultSet rs ;
    private static Statement statement;
    private static ResultSetMetaData resSetMetData;


     /*
    a static method to create connection
    with valid url and username, password
    */

    public static void createConnection(){
        String url = ConfigurationReader.getProperty("library1.database.url");
        String username = ConfigurationReader.getProperty("library1.database.username");
        String password = ConfigurationReader.getProperty("library1.database.password");

        //handle or declare

        try {
            con = DriverManager.getConnection(url , username , password);
            System.out.println("CONNECTION SUCCESSFUL!");
        } catch (SQLException e) {
            System.err.println("Connection has FAILED! Error occurred because of :  " + e.getMessage());
        }

//        createConnection(url , username , password);

    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /*
    Create connection by JDBC url, username and password provided
    @param : url
     @param : username
     @param : password
     */
    public static void createConnection(String url, String username, String password) {
        try {
            con = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL!");
        } catch (SQLException e) {
            System.err.println("Connection has FAILED! Error occurred because of :  "+e.getMessage());
        }
    }

    public static ResultSet runQuery (String query){

        try {
            statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE , ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(query);// setting the value of ResultSet object
            resSetMetData = rs.getMetaData();// setting the value of ResultSetMetaData for reuse
        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE RUNNING QUERY : "+ e.getMessage());
        }

        return rs ;
    }

    public static void destroy(){
        try {
            if ( con != null ) con.close();
            if (statement != null ) statement.close();
            if (rs != null ) rs.close();
        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE CLOSING RESOURCES : "+ e.getMessage());
        }
    }


    public static int getRowCount(){

        int rowCount = 0;
        try {
            rs.last();
            rowCount = rs.getRow();

        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE GETTING ROW COUNT : " + e.getMessage());
        }finally {
            resetCursor();
        }
        return rowCount;
    }


    /*
      find out the column count
      @return column count of this ResultSet
     */
    public static int getColumnCount (){
        int columnCount = 0;

        try {

            columnCount = resSetMetData.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS : " + e.getMessage());
        }

        return columnCount;
    }

///==========================================================================================


    //    Get all the column names into a list object
    public static List<String> getAllColumnNamesAsList(){

        List<String> columnNameList = new ArrayList<>();

        try {
            for (int colIndex = 1; colIndex <= getColumnCount(); colIndex++) {
                String columnName = resSetMetData.getColumnName(colIndex);
                columnNameList.add(columnName);
            }
        }catch (SQLException e){
            System.err.println("ERROR WHILE getAllColumnNamesAsList " + e.getMessage());
        }
        return columnNameList;
    }

///==========================================================================================


    //    get entire row of data according to row number
    public static List<String> getRowDataAsList (int rowNum ){

        List<String> rowDataAsList = new ArrayList<>();
        int colCount = getColumnCount();

        try {
            rs.absolute(rowNum);

            for (int colIndex = 1; colIndex <= colCount ; colIndex++) {

                String cellValue = rs.getString(colIndex);
                rowDataAsList.add( cellValue  );

            }

        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE getRowDataAsList : " + e.getMessage());
        }finally {
            resetCursor();
        }


        return rowDataAsList ;
    }

///==========================================================================================

//getting the cell value according to row num and column index

    public static String getCellValue(int rowNum , int columnIndex){
        String cellValue = "";


        try {

            rs.absolute(rowNum);
            cellValue = rs.getString(columnIndex);
            rs.beforeFirst();

        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE getCellValue : " + e.getMessage());
        }
        return cellValue;
    }


    //getting the cell value according to row num and column name

    public static String getCellValue(int rowNum , String columnName){
        String cellValue = "";

        try {

            rs.absolute(rowNum);
            cellValue = rs.getString(columnName);


        } catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE getCellValue : " + e.getMessage());
        } finally {
            resetCursor();
        }
        return cellValue;
    }

///==========================================================================================


    //getting entire column data as list according to column number
    //return List

    public static List<String> getColumnDataAsList(int columnNum) {

        List<String> columnDataList  = new ArrayList<>();
        try {

            rs.beforeFirst();
            while (rs.next()){

                String cellValue = rs.getString(columnNum);
                columnDataList.add(cellValue);

            }

        }catch (SQLException e ){
            System.err.println("ERROR OCCURRED WHILE getColumnDataAsList : " + e.getMessage());
        }finally {
            resetCursor();
        }

        return columnDataList;
    }


    //getting entire column data as list according to column Name
    //return List

    public static List<String> getColumnDataAsList(String columnName) {

        List<String> columnDataList  = new ArrayList<>();
        try {

            rs.beforeFirst();
            while (rs.next()){

                String cellValue = rs.getString(columnName);
                columnDataList.add(cellValue);

            }

        }catch (SQLException e ){
            System.err.println("ERROR OCCURRED WHILE getColumnDataAsList : " + e.getMessage());
        }finally {
            resetCursor();
        }

        return columnDataList;
    }
///==========================================================================================


    public static void resetCursor() {

        try {
            rs.beforeFirst();
        }catch (SQLException e) {
            System.err.println("ERROR OCCURRED WHILE resetCursor : " + e.getMessage());
        }
    }



///==========================================================================================

    //display all Data
    public static void  displayAllData(){

        int columnCount = getColumnCount() ;
        resetCursor();
        try{

            while(rs.next()){

                for (int colIndex = 1; colIndex <= columnCount; colIndex++) {

                    //System.out.print( rs.getString(colIndex) + "\t" );
                    System.out.printf("%-25s", rs.getString(colIndex));
                }
                System.out.println();

            }

        }catch(SQLException e){
            System.out.println("ERROR OCCURRED WHILE displayAllData " + e.getMessage() );
        }finally {
            resetCursor();
        }

    }


    //    ======================================================================================
   /* public static void displayAllDataNiceFormat() {
        try {
            int colCount = getColumnCount();
            for (int col = 1; col <= colCount; col++) {
                System.out.printf("%-22s", "|  " + resSetMetData.getColumnName(col));
            }
            System.out.println();
            for (int col = 1; col <= colCount; col++) {
                System.out.print("--------------------");
            }
            System.out.println();
            // THIS IS PRINTING EACH ROW DATA
            rs.beforeFirst();
            // now I want to go through each and every row
            while (rs.next()) {
                // this will print entire row
                for (int col = 1; col <= colCount; col++) {
                    //System.out.print(rs.getString(col) + "\t");
                    System.out.printf("%-22s", "|  " + rs.getString(col));
                }
                System.out.println();
            }
        }catch (SQLException e){
            System.out.println("ERROR OCCURRED WHILE displayAllData " + e.getMessage());
        }finally {
            resetCursor();
        }
    }*/
//    ======================================================================================


    /*public static void displayAllDataAsTable() {
        try {
            int colCount = getColumnCount();
            int[] cellLength = new int[colCount];
            for (int col = 1, i=0; col <=colCount ; col++, i++) {
                Statement stm2 = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs2 = stm2.executeQuery("select max(length("+col+")) from EMPLOYEES");
                rs2.first();
                cellLength[i] = Integer.parseInt(rs2.getString(1));
            }
            System.out.println("cellLength = " + Arrays.toString(cellLength));
            rs.beforeFirst(); // make sure the cursor is at before first location

            while (rs.next()) {
                for (int col = 1, i=0; col <= colCount; col++, i++) {
                    String cellValue = rs.getString(col);
                    System.out.printf("%-"+cellLength[i]+"s", " | "+ cellValue);
                }
                System.out.println();
            }
            rs.beforeFirst(); // reset the cursor to before first location
        } catch (SQLException e) {
            System.out.println("error occurred while displayAllData : " + e.getMessage());
        } finally {
            resetCursor();
        }
    }
*/

//    ======================================================================================

    //Save entire row data as Map<String , String>
    //key  - columnName
    //value - cell value


    public static Map<String,String> getRowMap(int rowNum){
        Map<String , String> rowMap = new LinkedHashMap<>();

        try {
            rs.absolute(rowNum);

            for (int colIndex = 1; colIndex <= getColumnCount() ; colIndex++) {
                String columnName = resSetMetData.getColumnName(colIndex);
                String columnValue = rs.getString(colIndex);

                rowMap.put(columnName , columnValue);

            }

        }catch (SQLException e){
            System.err.println("ERROR OCCURRED WHILE getRowMap : "+ e.getMessage());
        }finally {
            resetCursor();
        }



        return rowMap;
    }
//    ======================================================================================

    //We know how to store one row as map object
    //Now store all Rows as List of Map object
    //@return List of Map object that contains each row data as Map<String, String>

    public static List<Map<String , String>> getAllDataAsListOfMap () {

        List<Map<String, String>> allRowAsListOfMap = new ArrayList<>();

        // move from first row till last row
        //get each row as map object and add it to the list


        for (int rowIndex = 1; rowIndex <= getRowCount(); rowIndex++) {

            Map<String, String> rowMap = getRowMap(rowIndex);
            allRowAsListOfMap.add(rowMap);

        }

        resetCursor();

        return allRowAsListOfMap;

    }

//    ======================================================================================

     /*
    @return the first row first column data
     */

    public static String getFirstRowFirstColumn(){

        return getCellValue(1,1);

    }


}






