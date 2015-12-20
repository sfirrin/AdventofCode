import java.util.HashMap;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
// This is definitely not my best work but it does get the job done


public class Day7 {



    public static void main(String[] args) {
        File input = new File("Day7\\src\\day7.txt");


        try {
            Scanner instructions = new Scanner(input);
            HashMap<String, Wire> wireList = new HashMap<String,Wire>();

            do {
                while (instructions.hasNext()) {
                    String currentLine = instructions.nextLine();
                    System.out.println(currentLine);
                    String[] lineParts = currentLine.split(" ");
                    for (String s : lineParts) {
                        System.out.println(s);
                    }


                    if (lineParts.length == 3) {
                        // Check for direct assignment of signal to wire
                        try {
                            Wire currentWire = new Wire(lineParts[2], Integer.parseInt(lineParts[0]));
                            wireList.putIfAbsent(lineParts[2], currentWire);
                        } catch (NumberFormatException e) {
                            // Check for assignment of one wire's signal to another wire
                            Wire firstWire = new Wire(lineParts[0]);
                            wireList.putIfAbsent(lineParts[0], firstWire);

                            Wire secondWire = new Wire(lineParts[2], firstWire.getSignal());
                            wireList.putIfAbsent(lineParts[2], secondWire);

                        }
                    } else if (lineParts.length == 4) {
                        // Check for NOT assignment
                        wireList.putIfAbsent(lineParts[1], new Wire(lineParts[1]));
                        wireList.putIfAbsent(lineParts[3], new Wire(lineParts[3]));
                        if (wireList.get(lineParts[1]).getSignal() == -1) {
                            wireList.get(lineParts[3]).setSignal(-1);
                        } else {
                            int notSignal = wireList.get(lineParts[1]).getSignal();
                            wireList.get(lineParts[3]).setSignal(~notSignal);
                        }
                    } else if (lineParts.length == 5) {
                        String op = lineParts[1];
                        if (op.equals("AND")) {
                            wireList.putIfAbsent(lineParts[0], new Wire(lineParts[0]));
                            wireList.putIfAbsent(lineParts[2], new Wire(lineParts[2]));
                            wireList.putIfAbsent(lineParts[4], new Wire(lineParts[4]));
                            if (wireList.get(lineParts[0]).getSignal() == -1
                                    || wireList.get(lineParts[2]).getSignal() == -1) {
                                wireList.get(lineParts[4]).setSignal(-1);
                            } else {
                                int firstSignal = wireList.get(lineParts[0]).getSignal();
                                int secondSignal = wireList.get(lineParts[2]).getSignal();
                                wireList.get(lineParts[4]).setSignal((firstSignal & secondSignal));
                            }
                        } else if (op.equals("OR")) {
                            // I know this is a lot of repeated code that should go in a method but I've come this far
                            wireList.putIfAbsent(lineParts[0], new Wire(lineParts[0]));
                            wireList.putIfAbsent(lineParts[2], new Wire(lineParts[2]));
                            wireList.putIfAbsent(lineParts[4], new Wire(lineParts[4]));
                            if (wireList.get(lineParts[0]).getSignal() == -1
                                    || wireList.get(lineParts[2]).getSignal() == -1) {
                                wireList.get(lineParts[4]).setSignal(-1);
                            } else {
                                int firstSignal = wireList.get(lineParts[0]).getSignal();
                                int secondSignal = wireList.get(lineParts[2]).getSignal();
                                wireList.get(lineParts[4]).setSignal((firstSignal | secondSignal));
                            }
                        } else if (op.equals("LSHIFT")) {
                            int shiftAmount = Integer.parseInt(lineParts[2]);
                            wireList.putIfAbsent(lineParts[0], new Wire(lineParts[0]));
                            wireList.putIfAbsent(lineParts[4], new Wire(lineParts[4]));
                            if (wireList.get(lineParts[0]).getSignal() == -1) {
                                wireList.get(lineParts[4]).setSignal(-1);
                            } else {
                                int baseSignal = wireList.get(lineParts[0]).getSignal();
                                wireList.get(lineParts[4]).setSignal((baseSignal << shiftAmount));
                            }
                        } else if (op.equals("RSHIFT")) {
                            int shiftAmount = Integer.parseInt(lineParts[2]);
                            wireList.putIfAbsent(lineParts[0], new Wire(lineParts[0]));
                            wireList.putIfAbsent(lineParts[4], new Wire(lineParts[4]));
                            if (wireList.get(lineParts[0]).getSignal() == -1) {
                                wireList.get(lineParts[4]).setSignal(-1);
                            } else {
                                int baseSignal = wireList.get(lineParts[0]).getSignal();
                                wireList.get(lineParts[4]).setSignal((baseSignal >>> shiftAmount));
                            }
                        }
                    }

                }
            } while (!isComplete(wireList));

            System.out.println(wireList.get("a").getSignal());


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static boolean isComplete(HashMap<String,Wire> wireMap) {
        boolean complete = true;
        for (String key : wireMap.keySet()) {
            System.out.println(key);
            System.out.println(wireMap.get(key).getSignal());
            if (wireMap.get(key).getSignal() == -1) {
                complete = false;
                break;
            }
        }


        return complete;
    }
}
