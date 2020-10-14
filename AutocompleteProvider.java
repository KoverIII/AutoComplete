import java.util.List;
import java.util.ArrayList;

public class AutocompleteProvider{

    AutocompleteProvider(){
        candidates = new ArrayList<Candidate>();
        candidatesCopy = new ArrayList<String>();
    }

    List<Candidate> getWords(String fragment){

        List<Candidate> candidateList = new ArrayList<Candidate>(); 

        //Loop through candidates to find autocomplete candidates for the string the user input
        for(int i = 0; i < candidates.size(); i++){

            String word = candidates.get(i).getWord();

            if(word.contains(fragment) && word.indexOf(fragment) == 0){
                candidateList.add(candidates.get(i));
            }

        }

        sortCandidates(candidateList);

        System.out.print("Input: \"" + fragment + "\" --> ");

        return candidateList;

    }

    void train(String passage){
 
        String[] arr = passage.split("[.?! ]");
        int arrLength = arr.length;
        
        //Check if the candidate is a duplicate; if not, add it to the list; if so, then add 1 to the confidence
        for(int i = 0; i < arrLength; i++){
            
            Candidate newCandidate = new Candidate(arr[i]);                   

            if(!isDuplicate(newCandidate)){
                addCandidate(newCandidate);
            }
            
        }

        System.out.println("Train: \"" + passage + "\"");

    }

    void addCandidate(Candidate newCandidate){
        candidates.add(newCandidate);
    }

    void addCandidateCopy(Candidate newCandidate){
        candidatesCopy.add(newCandidate.getWord());
    }

    void printCandidates(List<Candidate> candidateList){

        //print all the candidates
        for(int i = 0; i < candidateList.size(); i++){
            
            if(i == candidateList.size() - 1){
                System.out.print("\"" + candidateList.get(i).getWord() + "\" (" + candidateList.get(i).getConfidence() + ") ");
            }
            else{
                System.out.print("\"" + candidateList.get(i).getWord() + "\" (" + candidateList.get(i).getConfidence() + "), ");
            }
        }
        System.out.println("\n");

    }
    
    boolean isDuplicate(Candidate newCandidate){

        int index = candidatesCopy.indexOf(newCandidate.getWord());

        //if the word already exists in candidatesCopy then the newCandidate is a duplicate
        if(index == -1){

            addCandidateCopy(newCandidate);
            return false;

        }
        else{

            candidates.get(index).setConfidence(candidates.get(index).getConfidence() + 1);  
            return true;

        }
        
    }

    void sortCandidates(List<Candidate> candidateList){
 
        //MergeSort used to order all of the candidates by confidence

        if(candidateList.size() >= 2){

            List<Candidate> left = new ArrayList<Candidate>(candidateList.size() / 2);
            List<Candidate> right = new ArrayList<Candidate>(candidateList.size() - candidateList.size() / 2);

            int leftLength = candidateList.size() / 2;
            int rightLength = candidateList.size() - candidateList.size() / 2;
            
            for(int i = 0; i < leftLength; i++){
                left.add(i,candidateList.get(i));
            }
            for(int j = 0; j < rightLength; j++){
                right.add(j, candidateList.get(j +candidateList.size()/2));
            }
            
            sortCandidates(left);
            sortCandidates(right);
            mergeCandidates(candidateList,left,right);

        }
        else{
            return;
        }

    }

    void mergeCandidates(List<Candidate> candidateList,List<Candidate> left, List<Candidate> right){

        int first = 0;
        int second = 0;

        //Merge the parts of the sort

        for (int i = 0; i < candidateList.size(); i++) {

            if (second >= right.size() || (first < left.size() && left.get(first).getConfidence() > right.get(second).getConfidence())){
   
                    candidateList.set(i, left.get(first));
                    first += 1;
              
            } 
            else 
            {

                candidateList.set(i, right.get(second));
                second += 1;

            }
        }
    }


    List<Candidate> candidates;
    List<String> candidatesCopy; //copy of candidates.word used to test for duplicates



}