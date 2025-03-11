package izly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class CodeSecretUnitTest {
    
    @Test
    public void testRevelerCode() {
        CodeSecret code = CodeSecret.createCode(new Random());
        Assertions.assertTrue(isCode(code.revelerCode()));
        Assertions.assertEquals("xxxx", code.revelerCode());
    }

    private boolean isCode(String code) {
        if (code.length()!=4) return false;
        try {
            Integer.parseInt(code);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Test
    public void testCodeCreeAleatoirement() {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(10)).thenReturn(1,2,3,4);
        CodeSecret codeSecret =  CodeSecret.createCode(random);
        Assertions.assertEquals("1234", codeSecret.revelerCode());
        Mockito.verify(random, Mockito.times(4)).nextInt(10);
        Assertions.assertEquals("xxxx", codeSecret.revelerCode());
    }

    @Test
    public void testVerifierCode() throws Exception {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(10)).thenReturn(1,2,3,4);
        CodeSecret codeSecret =  CodeSecret.createCode(random);
        Assertions.assertTrue(codeSecret.verifierCode("1234"));
        Assertions.assertFalse(codeSecret.verifierCode("9876"));
    }

    @Test
    public void testCodeNonBloqueApres3essaisFauxNonSuccessifsPuisBloqueApres3essaisFauxSuccessifs() throws Exception {
        Random random = Mockito.mock(Random.class);
        Mockito.when(random.nextInt(10)).thenReturn(1,2,3,4);
        CodeSecret codeSecret =  CodeSecret.createCode(random);
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("9876");
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("9876");
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("9876");
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("9876");
        Assertions.assertFalse(codeSecret.isBlocked());
        codeSecret.verifierCode("9876");
        Assertions.assertTrue(codeSecret.isBlocked());
    }

}
