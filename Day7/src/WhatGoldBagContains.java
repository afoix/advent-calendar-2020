import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WhatGoldBagContains {

    // Path to the file to read
    private static final String PATH_TO_YOUR_FILE = "/Users/afoix/dango-git/advent-calendar-2020/Day7/ruleForBags.txt";

    // Main function and reading all the line from file
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_YOUR_FILE));

        Map<String, Map<String, Integer>> allRules = new HashMap<>();

        while (true){
            String line = reader.readLine();
            if (line == null) {
                break;
            }

            // Parsing to figured out the the rules
            Pattern patter = Pattern.compile("^(\\w+ \\w+) bags contain (.+)$");
            Matcher matcher = patter.matcher(line);
            matcher.find();

            // What kind of bag is talking about and what the rule said about that bag
            String typeOfBag = matcher.group(1);
            String bagsInside = matcher.group(2);

            Pattern patternForBagsInside = Pattern.compile("(\\d+) (\\w+ \\w+)");
            Matcher matcherForBagsInside = patternForBagsInside.matcher(bagsInside);

            Map<String, Integer> bagsInsideSet = new HashMap<>();

            while (matcherForBagsInside.find()) {
                bagsInsideSet.put(matcherForBagsInside.group(2), Integer.decode(matcherForBagsInside.group(1)));
            }

            allRules.put(typeOfBag, bagsInsideSet);

        }

        Integer totalBagsInside = 0;

        Queue<String> bagsToCheck = new ArrayDeque<>();

        bagsToCheck.add("shiny gold");

        while (!bagsToCheck.isEmpty()) {
            String bag = bagsToCheck.poll();
            for ( Map.Entry<String, Integer> entry : allRules.get(bag).entrySet()) {
                for (int bagNumber = 0; bagNumber < entry.getValue(); bagNumber++) {
                    totalBagsInside++;
                    bagsToCheck.add(entry.getKey());
                }
            }
        }

        System.out.println(String.format("Number of bags that must be inside a shiny gold bag: %d", totalBagsInside));

    }
}
