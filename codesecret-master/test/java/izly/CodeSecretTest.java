package izly;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class CodeSecretTest {

    @Test
    public void testVerifierCodeAvecCodeValide() throws CodeBlockedException {
        CodeSecret codeSecret = CodeSecret.creerCodeValide();
        Assertions.assertTrue(codeSecret.verifierCode("8888"), "Le code valide devrait être vérifié avec succès.");
    }

    @Test
    public void testVerifierCodeAvecCodeInvalide() {
        CodeSecret codeSecret = CodeSecret.creerCodeValide();
        Assertions.assertThrows(CodeBlockedException.class, () -> {
            for (int i = 0; i < 4; i++) {
                codeSecret.verifierCode("1234");
            }
        }, "Devrait lancer une CodeBlockedException après plusieurs tentatives incorrectes.");
    }

    @Test
    public void testIsBlockedApresTroisTentativesIncorrectes() throws CodeBlockedException {
        CodeSecret codeSecret = CodeSecret.creerCodeValide();
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        codeSecret.verifierCode("1234");
        Assertions.assertTrue(codeSecret.isBlocked(), "Le code devrait être bloqué après trois tentatives incorrectes.");
    }

    @Test
    public void testRevelerCodeApresBlocage() {
        CodeSecret codeSecret = CodeSecret.creerCodeBloque();
        Assertions.assertEquals("8888", codeSecret.revelerCode(), "Le code devrait être révélé même si bloqué.");
        Assertions.assertEquals("xxxx", codeSecret.revelerCode(), "Le code ne devrait pas être révélé une seconde fois.");
    }
}
