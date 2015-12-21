    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Scanner;

    public class Day7 {
        static HashMap<String, Integer> wires;
        static boolean part2 = true;

        public static void main(String[] args) throws FileNotFoundException {
            ArrayList<String[]> instList = new ArrayList<String[]>();
            File input = new File("Day7\\src\\day7.txt");
            Scanner fileIn = new Scanner(input);
            while (fileIn.hasNextLine()) {
                String currentLine = fileIn.nextLine();
                String[] lineParts = currentLine.split(" ");
                instList.add(lineParts);
            }

            wires = new HashMap<String, Integer>();
            if (part2) wires.put("b", 3176);

            while (!wires.containsKey("a")) {
                for (int i=0; i<instList.size(); i++) {
                    String[] currentInst = instList.get(i);
                    if (currentInst.length == 3) {
                        // Direct assignment of a signal or wire's signal to another wire
                        if (!wires.containsKey(currentInst[2])) {
                            if (currentInst[0].matches("\\d+")) {
                                wires.put(currentInst[2], Integer.parseInt(currentInst[0]));
                            } else {
                                if (wires.containsKey(currentInst[0])) {
                                    wires.put(currentInst[2], wires.get(currentInst[0]));
                                }
                            }
                        }

                    } else if (currentInst.length == 4) {
                        // NOT assignment
                        if (!wires.containsKey(currentInst[3])) {
                            if (currentInst[1].matches("\\d+")) {
                                wires.put(currentInst[3], (~Integer.parseInt(currentInst[1]) & 0xffff));
                            } else if (wires.containsKey(currentInst[1])) {
                                int notSignal = wires.get(currentInst[1]);
                                wires.put(currentInst[3], (~notSignal & 0xffff));
                            }
                        }

                    } else if (currentInst.length == 5) {
                        String first = currentInst[0];
                        String second = currentInst[2];
                        String last = currentInst[4];
                        String op = currentInst[1];
                        Integer firstInt = null;
                        if (first.matches("\\d+")) {
                            firstInt = Integer.parseInt(first);
                        } else if (wires.containsKey(first)) {
                            firstInt = wires.get(first);
                        }

                        if (!wires.containsKey(last) && firstInt != null) {
                            if (op.equals("LSHIFT")) {
                                wires.put(last, firstInt << Integer.parseInt(second));
                            } else if (op.equals("RSHIFT")) {
                                wires.put(last, firstInt >>> Integer.parseInt(second));
                            } else if (wires.containsKey(second)) {
                                if (op.equals("AND")) {
                                    wires.put(last, firstInt & wires.get(second));
                                } else if (op.equals("OR")) {
                                    wires.put(last, firstInt | wires.get(second));
                                }
                            }
                        }
                    }
                }

            }

            System.out.println("The value of a is: " + wires.get("a"));
        }
    }