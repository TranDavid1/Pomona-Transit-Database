//David Kim and David Tran
import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class Driver {
    public static void main(String[] args)
    {
        boolean loop = true;
        while (loop == true) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Pomona Transit Database, what option would you like to choose?");
        System.out.println("1: Display the schedule of all trips");
        System.out.println("2: Delete a trip offering");
        System.out.println("3: Add a trip offering");
        System.out.println("4: Change the driver for a given trip offering");
        System.out.println("5: Change the bus for a given trip offering");
        System.out.println("6: Display the stops of a given trip");
        System.out.println("7: Display the weekly schedule of a given driver and dates");
        System.out.println("8: Add a driver");
        System.out.println("9: Add a bus");
        System.out.println("10: Delete a bus");
        System.out.println("11: Insert the actual data of a given trip offering");
        System.out.print("Please enter an integer: ");
        int caseInt = scanner.nextInt();

            switch (caseInt) {
                case 1:
                    // Call displaySchedule method
                    displaySchedule();

                    loop = checkLoop();
                    break;

                case 2:
                    // Call deleteTripOffering
                    deleteTripOffering();

                    loop = checkLoop();
                    break;

                case 3:
                    boolean addTripsLoop = true;
                    while (addTripsLoop = true) {
                        // Call addTripOfferings
                        addTripOfferings();

                        System.out.println("Would you like to enter more trips? Please enter 'Y' or 'Yes'' if you would.");
                        scanner.nextLine();
                        String userInput = scanner.nextLine();
                        if (userInput.equals("Y") || userInput.equals("Yes"))
                            addTripsLoop = true;
                        else {
                            addTripsLoop = false;
                            break;
                        }

                    }
                    loop = checkLoop();
                    break;

                case 4:
                    // Call changeDriver
                    changeDriver();

                    loop = checkLoop();
                    break;

                case 5:
                    // Call changeBus
                    changeBus();

                    loop = checkLoop();
                    break;

                case 6:
                    // Call displayStops
                    displayStops();

                    loop = checkLoop();
                    break;

                case 7:
                    // Call displayWeeklySchedule
                    displayWeeklySchedule();

                    loop = checkLoop();
                    break;

                case 8:
                    // Call addDriver
                    addDriver();

                    loop = checkLoop();
                    break;

                case 9:
                    // Call addBus
                    addBus();

                    loop = checkLoop();
                    break;

                case 10:
                    // Call deleteBus
                    deleteBus();

                    loop = checkLoop();
                    break;

                case 11:
                    // Call insertActualData
                    insertActualData();

                    loop = checkLoop();
                    break;

                default:
                    printPrompt();
            }
        }
    }

    public static Boolean checkLoop() {
        System.out.println("Would you like to continue? Please enter 'Y' or 'Yes' to continue: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        if(userInput.equals("Y") || userInput.equals("Yes"))
        {
            return true;
        } else {
            return false;
        }
    }

    public static void printPrompt() {
        System.out.println("Welcome to the Pomona Transit Database, what option would you like to choose?");
        System.out.println("1: Display the schedule of all trips");
        System.out.println("2: Edit the schedule");
        System.out.println("3: Delete a trip offering");
        System.out.println("4: Add a trip offering");
        System.out.println("5: Change the driver for a given trip offering");
        System.out.println("6: Change the bus for a given trip offering");
        System.out.println("7: Display the stops of a given trip");
        System.out.println("8: Display the weekly schedule of a given driver and dates");
        System.out.println("9: Add a driver");
        System.out.println("10: Add a bus");
        System.out.println("11: Delete a bus");
        System.out.println("12: Insert the actual data of a given trip offering");
        System.out.print("Please enter an integer: ");
    }

    public static void displaySchedule()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.print("Enter Start Location: ");
            String startLocation = scanner.nextLine();
            System.out.print("Enter Destination Name: ");
            String destination = scanner.nextLine();
            System.out.print("Enter Date: ");
            String dates = scanner.nextLine();

            ResultSet resultSet = statement.executeQuery("SELECT A.ScheduledStartTime, A.ScheduledArrivalTime, A.DriverName, A.BusID FROM TripOffering A, Trip B WHERE B.StartLocationName = '" + startLocation + "' AND B.DestinationName = '" + destination +
                    "' AND A.Dates = '" + dates + "' AND A.TripNumber = B.TripNumber ");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("ScheduledStartTime"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("ScheduledArrivalTime"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("DriverName"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("BusID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteTripOffering()
    {
        try
        {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.print("Enter Trip Number: ");
            int number = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Date: ");
            String dates = scanner.nextLine();
            System.out.print("Enter Scheduled Start Time: ");
            String startTime = scanner.nextLine();

            statement.execute("DELETE FROM TripOffering WHERE TripNumber = '" + number + "' AND Dates = '" + dates + "' AND ScheduledStartTime = '" + startTime + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTripOfferings()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Trip Number:");
            String number = scanner.nextLine();
            System.out.println("Enter Date:");
            String dates = scanner.nextLine();
            System.out.println("Enter Scheduled Start Time:");
            String startTime = scanner.nextLine();
            System.out.println("Enter Scheduled Arrival Time:");
            String arrivalTime = scanner.nextLine();
            System.out.println("Enter Driver Name:");
            String name = scanner.nextLine();
            System.out.println("Enter Bus ID:");
            String id = scanner.nextLine();

//            ResultSet resultSet =
            statement.execute("INSERT INTO TripOffering VALUES ('"+ number +"', '" + dates +"', '" + startTime+"', '" + arrivalTime+"', '" + name+"', '" + id+"')");

//            ResultSetMetaData rsMeta = resultSet.getMetaData();

//            while (resultSet.next()) {
//                System.out.println(resultSet.getString("TripNumber"));
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeDriver()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Trip Number:");
            String number = scanner.nextLine();
            System.out.println("Enter Date:");
            String dates = scanner.nextLine();
            System.out.println("Enter Scheduled Start Time:");
            String startTime = scanner.nextLine();
            System.out.println("Enter a new Driver Name:");
            String name = scanner.nextLine();

            statement.executeUpdate("UPDATE TripOffering " + "SET DriverName = '" + name + "' WHERE TripNumber = '" + number + "' AND Dates = '" + dates + "' AND ScheduledStartTime = '" + startTime + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void changeBus()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Trip Number:");
            String number = scanner.nextLine();
            System.out.println("Enter Date:");
            String date = scanner.nextLine();
            System.out.println("Enter Scheduled Start Time:");
            String startTime = scanner.nextLine();
            System.out.println("Enter a new Bus ID:");
            String id = scanner.nextLine();

            statement.executeUpdate("UPDATE TripOffering SET BusID = '" + id + "' WHERE TripNumber = '" + number + "' AND Dates = '" + date + "' AND ScheduledStartTime = '" + startTime + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayStops()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Trip Number:");
            String number = scanner.nextLine();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM TripStopInfo WHERE TripNumber = '" + number + "'");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("TripNumber"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("StopNumber"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("SequenceNumber"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("DrivingTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayWeeklySchedule()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");
            Statement statement = connection.createStatement();

            System.out.println("Enter Date:");
            String date = scanner.nextLine();
            System.out.println("Enter Driver Name:");
            String name = scanner.nextLine();

            ResultSet resultSet = statement.executeQuery("SELECT TripNumber, Dates, ScheduledStartTime, ScheduledArrivalTime, BusID FROM TripOffering WHERE DriverName = '" + name + "' AND Dates = '" + date + "'");

            while (resultSet.next()) {
                System.out.print(resultSet.getString("TripNumber"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("Dates"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("ScheduledStartTime"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("ScheduledArrivalTime"));
                System.out.print(" | ");
                System.out.print(resultSet.getString("BusID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDriver()
    //works
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter New Driver Name:");
            String name = scanner.nextLine();
            System.out.println("Enter phone number:");
            String number = scanner.nextLine();

            statement.execute("INSERT INTO Driver VALUES ('"+ name +"', '" + number +"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addBus()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter New BusID: ");
            String BusID = scanner.nextLine();
            System.out.println("Enter model: ");
            String model = scanner.nextLine();
            System.out.println("Enter year: ");
            String year = scanner.nextLine();

            statement.execute("INSERT INTO Bus VALUES ('"+ BusID +"', '" + model +"', '" + year +"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteBus()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Bus ID:");
            String id = scanner.nextLine();

            statement.executeUpdate("DELETE FROM Bus WHERE BusID = '" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertActualData()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            Connection connection = DriverManager.getConnection("jdbc:mysql://205.185.117.17:3306/lab3","test","password");

            Statement statement = connection.createStatement();
            System.out.println("Enter Trip Number:");
            String number = scanner.nextLine();
            System.out.println("Enter Date:");
            String date = scanner.nextLine();
            System.out.println("Enter Scheduled Start Time:");
            String startTime = scanner.nextLine();
            System.out.println("Enter Scheduled Arrival Time:");
            String arrivalTime = scanner.nextLine();
            System.out.println("Enter Stop Number:");
            String stopNumber = scanner.nextLine();
            System.out.println("Enter Actual Start Time:");
            String actualStartTime = scanner.nextLine();
            System.out.println("Enter Actual Arrival Time:");
            String actualArrivalTime = scanner.nextLine();
            System.out.println("Enter # of Passengers In:");
            String passIn = scanner.nextLine();
            System.out.println("Enter # of Passengers Out:");
            String passOut = scanner.nextLine();

            statement.execute("INSERT INTO ActualTripStopInfo VALUES ('"+ number +"', '" + date +"', '" + startTime+"', '" + stopNumber+"', '"+ arrivalTime+"', '"+ actualStartTime+"', '"+ actualArrivalTime+"', '" + passIn+"', '" + passOut+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}