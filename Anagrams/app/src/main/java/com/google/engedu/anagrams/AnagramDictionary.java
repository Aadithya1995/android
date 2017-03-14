/* Copyright 2016 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.444444
 */

package com.google.engedu.anagrams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    int wordlength = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    HashSet<String> wordset;
    HashMap<String, ArrayList<String>> lettersToWord;
    ArrayList<String> anagrams;
    HashMap<Integer, ArrayList<String>> lengthtoword;
    Random rand=new Random();

    public AnagramDictionary(Reader reader) throws IOException {
        String revWord;
        BufferedReader in = new BufferedReader(reader);
        String line;
        String revWord1;
        lettersToWord = new HashMap<>();
        wordset = new HashSet<>();
        lengthtoword = new HashMap<>();
        ArrayList<String> wordofl;


        while ((line = in.readLine()) != null) {
            String word = line.trim();
            wordset.add(word);
            revWord = sortit(word);

            if (!lengthtoword.containsKey(word.length())) {
                wordofl = new ArrayList<>();
                wordofl.add(word);
                lengthtoword.put(word.length(), wordofl);
            } else {
                wordofl = lengthtoword.get(word.length());
                wordofl.add(word);
                lengthtoword.put(word.length(), wordofl);
            }

            if (lettersToWord.containsKey(revWord)) {
                anagrams = lettersToWord.get(revWord);
                anagrams.add(word);
                lettersToWord.put(revWord, anagrams);

            } else {
                anagrams = new ArrayList<>();
                anagrams.add(word);
                lettersToWord.put(revWord, anagrams);
            }


        }
    }

    String sortit(String s) {
        char a[];
        a = s.toCharArray();
        //System.out.println(a);
        Arrays.sort(a);
        String k = new String(a);
        return k;
        //System.out.println(k);
    }

    public boolean isGoodWord(String word, String base) {
        if (wordset.contains(word) && (!word.contains(base)))
            return true;
        return false;
    }

    public List<String> getAnagrams(String targetWord) {//not used
        String revword;
        String newWord;
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> result1 = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            newWord = targetWord + i;
            revword = sortit(newWord);
            if (lettersToWord.containsKey(revword)) {


                anagrams = lettersToWord.get(revword);
                result.addAll(anagrams);
            }
        }
        for (String s : result) {
            if (!s.contains(targetWord)) {
                result1.add(s);
            }
        }


        return result1;
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();
        return result;
    }

    public String pickGoodStarterWord() {
        ArrayList<String> list;

        list = lengthtoword.get(wordlength);

        int i=rand.nextInt(list.size());

        for(int j=i;j<=list.size();j++)
        {
            if(getAnagrams(list.get(j)).size()>=5)
            {
                wordlength++;
                return list.get(j);
            }
        }



        for (String s : list) {
            if (getAnagrams(s).size() >= 5) {
                wordlength++;
                return s;
            }

        }
        return "badge";
    }


    }


