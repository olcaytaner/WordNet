package WordNet;

import Dictionary.Pos;
import Dictionary.TurkishWordComparator;
import Dictionary.TxtDictionary;
import org.junit.Before;
import org.junit.Test;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WordNet1955Test extends PreviousWordNetTest{

    @Before
    public void setUp() {
        previuosWordNet = new WordNet("turkish1955_wordnet.xml", new Locale("tr"));
        previousDictionary = new TxtDictionary("turkish1955_dictionary.txt", new TurkishWordComparator());
    }

    @Test
    public void testExistenceOfKeNetSynSets(){
        WordNet turkish = new WordNet();
        boolean found = true;
        for (SynSet synSet : previuosWordNet.synSetList()){
            if (synSet.getId().startsWith("TUR10") && turkish.getSynSetWithId(synSet.getId()) == null){
                System.out.println("SynSet with id " + synSet.getId() + " does not exist");
                found = false;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testSize() {
        assertEquals(34626, previuosWordNet.size());
    }

    @Test
    public void generateDictionary() {
        generateDictionary("1955");
    }

    @Test
    public void testSynSetList() {
        int literalCount = 0;
        for (SynSet synSet : previuosWordNet.synSetList()){
            literalCount += synSet.getSynonym().literalSize();
        }
        assertEquals(44813, literalCount);
    }

    @Test
    public void testGetSynSetsWithPartOfSpeech() {
        assertEquals(18329, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.NOUN).size());
        assertEquals(8042, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.VERB).size());
        assertEquals(5816, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.ADJECTIVE).size());
        assertEquals(1188, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.INTERJECTION).size());
        assertEquals(1075, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.ADVERB).size());
        assertEquals(84, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.CONJUNCTION).size());
        assertEquals(49, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.PREPOSITION).size());
        assertEquals(43, previuosWordNet.getSynSetsWithPartOfSpeech(Pos.PRONOUN).size());
    }

    @Test
    public void testLiteralSortedList() {
        assertTrue(previuosWordNet.literalCorrectOrderCheck());
    }

    @Test
    public void testLiteralList() {
        assertEquals(34178, previuosWordNet.literalList().size());
    }

    @Test
    public void testSameLiteralSameSenseCheck() {
        for (Literal literal : previuosWordNet.sameLiteralSameSenseCheck()){
            System.out.println(literal.getName());
        }
        assertEquals(0, previuosWordNet.sameLiteralSameSenseCheck().size());
    }

    @Test
    public void testNoPosCheck() {
        assertEquals(0, previuosWordNet.noPosCheck().size());
    }

    @Test
    public void testNoDefinitionCheck() {
        assertEquals(0, previuosWordNet.noDefinitionCheck().size());
    }

    @Test
    public void testSameLiteralSameSynSetCheck() {
        assertEquals(0, previuosWordNet.sameLiteralSameSynSetCheck().size());
    }

}
