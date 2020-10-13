public class Candidate {
    
    private int confidence;
    private String word;

    Candidate(String candidateWord){
        word = candidateWord;
        confidence = 1;
    }

    String getWord(){
        return word;
    }

    int getConfidence(){
        return confidence;
    }  

    void setConfidence(int newConfidence){
        confidence = newConfidence;
    }
}
