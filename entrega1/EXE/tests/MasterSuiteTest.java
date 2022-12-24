package EXE.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;




@RunWith(value = Suite.class)
// Afegeix al array <nomtest>.class de la classe que vols que es comprovi al fer # make fulltest
@SuiteClasses(value = { 
    DocumentTest.class, IndexEspaiVectorialTest.class, IndexTitolTest.class, TestExpressioBooleana.class, TestIndexAutors.class, TestIndexExpressionsBooleanes.class, TestNodeExpressio.class, Vector_BM25Test.class, Vector_TFIDFTest.class
})
public class MasterSuiteTest {}
