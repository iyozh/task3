package com.company;

import com.github.freva.asciitable.AsciiTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HelpTable {
    public void generateHelpTable(String[] headers,String[][] gameScheme) {
        List<String> headersForTable = new ArrayList<>();;
        headersForTable.add(0, "YOU / PC");
        headersForTable.addAll(Arrays.stream(headers).toList());
        int stringTableNum = 0;
        for (String move: headers) {
            List<String> tableString = new ArrayList<>();
            tableString.add(0, move);
            tableString.addAll(Arrays.stream(gameScheme[stringTableNum]).toList());
            gameScheme[stringTableNum] = tableString.toArray(new String[tableString.size()]);
            stringTableNum++;
        }
        String[] finalHeaders = headersForTable.toArray(new String[headersForTable.size()]);
        System.out.println(AsciiTable.getTable(finalHeaders, gameScheme));
    }
}
