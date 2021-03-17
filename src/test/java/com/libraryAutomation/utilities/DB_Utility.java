package com.libraryAutomation.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    /*
    a static method to create connection
    with valid url and username, password
    */
    public static void createConnection() {
        String connectionStr = ConfigurationReader.getProperty("library1.database.url");
        String username =  ConfigurationReader.getProperty("library1.database.username");
        String password =  ConfigurationReader.getProperty("library1.database.password");

        try {
            connection = DriverManager.getConnection(connectionStr, username, password);

            System.out.println("CONNECTION SUCCESSFUL");

        } catch (SQLException e) {
            System.err.println("Connection has FAILED! Error occurred because of :  " + e.getMessage());
        }

    }

    public static ResultSet runQuery(String query) {

        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("ERROR WHILE GETTING RESULT SET" + e.getMessage());
        }

        return rs;
    }

    public static void destroy() {


        try {
            if (rs != null) {
                rs.close();
            }

            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * Get the row count of the resultSet
     * @return the row number of the resultSet given
     */
    public static int getRowCount() {
        int rowCount = 0;
        try {

            rs.last();
            rowCount = rs.getRow();
            rs.beforeFirst();

        } catch (SQLException e) {
            System.err.println("ERROR WHILE GETTING ROW COUNT : " + e.getMessage());
        }

        return rowCount;
    }


    /*
     * a method to get the column count of the current ResultSet
     *
     *   getColumnCNT()
     * */
    public static int getColumnCNT() {
        int columnCount = 0;

        try {

            ResultSetMetaData resSetMetData = rs.getMetaData();
            columnCount = resSetMetData.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS : " + e.getMessage());
        }

        return columnCount;
    }


    /*
    CREATE A METHOD THAT CAN RETURN ALL COLUMN NAMES AS LIST
    List<String>
     */
    public static List<String> getColumnNames() {

        List<String> columnNamesList = new ArrayList<>();
        try {
            ResultSetMetaData resSetMetData = rs.getMetaData();

            for (int column = 1; column <= resSetMetData.getColumnCount(); column++) {

                String columnName = resSetMetData.getColumnName(column);

                columnNamesList.add(columnName);

            }


        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING THE COLUMN NAMES : " + e.getMessage());
        }

        return columnNamesList;
    }


    /*
    TO GET THE ENTIRE ROW with DATA
     List of Strings that contains the row data
     */
    public static List<String> getRowDataAsList(int rowNum) {
        List<String> rowDataList = new ArrayList<>();

        try {
            rs.absolute(rowNum);
            for (int colNum = 1; colNum <= getColumnCNT(); colNum++) {
                String cellValue = rs.getString(colNum);
                rowDataList.add(cellValue);
            }
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataAsList: " + e.getMessage());
        }

        return rowDataList;

    }


    /*
    Getting single column cell value at certain row, using columnIndex
    1st parameter: row number we want to get data from
    2nd parameter: column index we want to get the data from
    return the data in String
    */
    public static String getColumnDataAtRow(int rowNum, int columnIndex) {

        String result = "";
        try {
            rs.absolute(rowNum);
            result = rs.getString(columnIndex);
            rs.beforeFirst();
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
        }
        return result;
    }


    /*
    par1 : rowNum
    par2 : columnName
    @return the data at that row with that column name
    */
    public static String getColumnDataAtRow(int rowNum, String columnName) {
        String result = "";

        try {
            rs.absolute(rowNum);
            result = rs.getString(columnName);

            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
        }

        return result;

    }


    /*
    @parameter columnIndex : the column you want to get  a list out of
    @return List of String that contains entire column data from 1st row to last row
    */
    public static List<String> getColumnDataAsList(int columnIndex) {
        List<String> columnDataList = new ArrayList<>();

        try {

            rs.beforeFirst();

            while (rs.next()) {

                String cellValue = rs.getString(columnIndex);
                columnDataList.add(cellValue);

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.err.println("ERROR WHILE getColumnDataAsList " + e.getMessage());
        }

        return columnDataList;
    }




    /*
    @parameter columnIndex : the column you want to get  a list out of
    @return List of String that contains entire column data from the columnName specified
    */
    public static List<String> getColumnDataAsList(String columnName) {
        List<String> columnDataList = new ArrayList<>();

        try {

            rs.beforeFirst();

            while (rs.next()) {

                String cellValue = rs.getString(columnName);
                columnDataList.add(cellValue);

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.err.println("ERROR WHILE getColumnDataAsList " + e.getMessage());
        }

        return columnDataList;
    }



    /*
    a method to display all the data in the result set
    */
    public static void displayAllData() {

        try {
            rs.beforeFirst();

            while (rs.next()) {

                for (int colNum = 1; colNum <= getColumnCNT(); colNum++) {
                    System.out.print(rs.getString(colNum) + "\t");
                }

                System.out.println();
            }

        } catch (SQLException e) {
            System.err.println("ERROR WHILE DISPLAYING ALL DATA : " + e.getMessage());

        }

    }



    /*We want to store certain row data as a Map<String , String>
    for ex: give me number 3rd row   --->> Map<String,String> {region_id : 3 , region_name : Asia}
    @param : rowNum
    @return Map object that contains column names as a key and cell as value
     */

    public static Map<String, String> getRowMap(int rowNum) {
        //  region_id,region_name
        Map<String, String> rowMap = new LinkedHashMap<>(); // keep insertion order
        try {
            rs.absolute(rowNum);
            ResultSetMetaData resSetMetaData = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCNT(); colNum++) {

                String colName = resSetMetaData.getColumnName(colNum);
                String cellValue = rs.getString(colNum);
                rowMap.put(colName, cellValue);

            }
            rs.beforeFirst();

        } catch (SQLException e) {
            System.err.println("ERROR AT ROW MAP FUNCTION " + e.getMessage());
        }

        return rowMap;

    }



    /*
        Getting entire resultSet data as List of Map object
        @Return List<  Map<String,String> > that represent all row data
        */
    public static List<Map<String, String>> getAllDataAsListOfMap() {

        List<Map<String, String>> rowMapList = new ArrayList<>();

        for (int rowNum = 1; rowNum <= getRowCount(); rowNum++) {

            rowMapList.add(getRowMap(rowNum));

        }

        return rowMapList;

    }




    /*
    @return the first row first column data
     */

    public static String getFirstData(){

        return getColumnDataAtRow(1,1);

    }







    public static void main(String[] args) throws SQLException {
        createConnection();
        ResultSet myResult = runQuery("SELECT * FROM REGIONS");
        /*rs.next();

        System.out.println( rs.getString(1));


        System.out.println( getRowCount() );
        System.out.println( getColumnCNT() );
        System.out.println( getColumnNames() );
        System.out.println( getRowDataAsList(3) );

        System.out.println("3rd row second column " + getColumnDataAtRow(3,2)) ;

        System.out.println("3rd row REGION_NAME column " + getColumnDataAtRow(3 , "REGION_NAME"));

        System.out.println("1st column as list :  " + getColumnDataAsList(1));

        System.out.println("1st column as list using columnName: " + getColumnDataAsList("REGION_ID"));
*/

//        displayAllData();

        System.out.println(getRowMap(3));

        Map<String, String> thirdRowMap = getRowMap(3);

        System.out.println("get the last name  : " + thirdRowMap.get("LAST_NAME"));

        System.out.println(getAllDataAsListOfMap());

//        System.out.println(getFirstData());

        destroy();

    }


}






