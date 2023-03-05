package ru.bstu.it191.chernih.lab1andlab2;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import ru.bstu.it191.chernih.lab1andlab2.array.ArrayUtils;
import ru.bstu.it191.chernih.lab1andlab2.conditional.NumberUtils;
import ru.bstu.it191.chernih.lab1andlab2.conditional.Point;
import ru.bstu.it191.chernih.lab1andlab2.cycle.FunctionCalculator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static void consoleUI() {
        logger.info("ConsoleUI method start");
        var scanner = new Scanner(System.in);
        try {
            logger.info("User task input begin");

            System.out.println("1 - three points\n2 - num string\n3 - function\n4 - arrays");
            var task = scanner.nextInt();

            logger.info("User task input: " + task);
            logger.info("User data mode input begin");

            System.out.println("1 - file\n2 - console");
            var mode = scanner.nextInt();

            logger.info("User data mode input: " + mode);

            if (mode == 1) {
                logger.info("User selected file");
                System.out.print("Enter filename: ");
                scanner.nextLine();
                var fileName = scanner.nextLine();
                logger.info("Users filename: " + fileName);
                scanner = new Scanner(new FileReader(fileName));
            } else if (mode == 2) {
                logger.info("User selected console");
                System.out.println("Enter data: ");
            } else throw new IllegalArgumentException();

            switch (task) {
                case 1: {
                    logger.info("User selected three points");
                    logger.info("Start point reading");

                    System.out.print("Point A: ");
                    var aX = scanner.nextDouble();
                    var aY = scanner.nextDouble();
                    System.out.println("{" + aX + ";" + aY + "}");

                    logger.info("A point {" + aX + ";" + aY + "}");

                    System.out.print("Point B: ");
                    var bX = scanner.nextDouble();
                    var bY = scanner.nextDouble();
                    System.out.println("{" + bX + ";" + bY + "}");

                    logger.info("B point {" + bX + ";" + bY + "}");

                    System.out.print("Point C: ");
                    var cX = scanner.nextDouble();
                    var cY = scanner.nextDouble();
                    System.out.println("{" + cX + ";" + cY + "}");

                    logger.info("C point {" + cX + ";" + cY + "}");

                    var pointA = new Point(aX, aY);
                    var pointB = new Point(bX, bY);
                    var pointC = new Point(cX, cY);

                    var distanceAB = Point.calcDistance(pointA, pointB);
                    var distanceAC = Point.calcDistance(pointA, pointC);
                    System.out.println("Distance between A and B: " + distanceAB);
                    logger.info("Distance between A and B: " + distanceAB);
                    System.out.println("Distance between A and C: " + distanceAC);
                    logger.info("Distance between A and C: " + distanceAC);
                    System.out.println("Point ".concat(distanceAB > distanceAC ? "C" : "B") + " is closer");
                    break;
                }
                case 2: {
                    logger.info("User selected num string");
                    logger.info("Start nums reading");

                    System.out.println("Nums");
                    while (scanner.hasNextLine()) {
                        var num = scanner.nextInt();

                        logger.info("User input: " + num);

                        System.out.println(NumberUtils.stringValue(num));
                    }
                    break;
                }
                case 3: {
                    logger.info("User selected function");
                    logger.info("Start reading parameters");

                    System.out.print("a: ");
                    var a = scanner.nextDouble();

                    logger.info("Selected parameter a: " + a);

                    System.out.print("b: ");
                    var b = scanner.nextDouble();

                    logger.info("Selected parameter b: " + b);

                    System.out.print("n: ");
                    var n = scanner.nextDouble();

                    logger.info("Selected parameter n: " + n);

                    logger.info("Start function calculator");
                    FunctionCalculator.printFunctionTableWithForCycle(
                            (x) -> Math.sin(x) + Math.tan(x), a, b, n);
                    break;
                }
                case 4: {
                    logger.info("User selected arrays");
                    logger.info("Start array reading");

                    // первым должена идти длина последовательности
                    System.out.print("arr length = ");
                    var len = scanner.nextInt();

                    logger.info("Array length = " + len);

                    var arr = new int[len];
                    for (int i = 0; i < len; i++) {
                        arr[i] = scanner.nextInt();
                    }
                    logger.info("Array: " + Arrays.toString(arr));
                    System.out.println(ArrayUtils.filterPositivesNegatives(arr));
                    break;
                }
                default: throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("corrupted data");
            logger.error(e);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            logger.error(e);
        }
    }


    public static void main(String[] args) {
        DOMConfigurator.configure("log4j.xml");
        logger.info("Program starts with parameters: " + Arrays.toString(args));
        consoleUI();
        logger.info("Program ended");

    }
}
