package izly;

import java.util.Random;

public class CodeSecret {
    private String code;
    boolean codeRevele;
    private int nbEssaiErreur;

    public static CodeSecret createCodeSecret(Random randomGenerator) {
        StringBuilder codeBuilder = new StringBuilder();
        for(int i=0;i<4;i++){
            codeBuilder.append(randomGenerator.nextInt(10));
        }
        String leCode = codeBuilder.toString();
        return new CodeSecret(leCode);
    }

    private CodeSecret(String code){
        this.code = code;
    }

    public Boolean verifierCode(String code) throws CodeBlockedException {
        if(isBlocked()) {
            throw new CodeBlockedException();
        }
        if(this.code.equals(code)) {
            nbEssaiErreur = 0;
            return true;
        } else {
            nbEssaiErreur++;
            return false;
        }
    }

    public Boolean isBlocked() {
        return nbEssaiErreur >= 3;
    }

    public String revelerCode() {
        if(!codeRevele){
            codeRevele = true;
            return code;
        }
        return "xxxx";
    }

    public static CodeSecret creerCodeBloque() {
        CodeSecret codeSecretBloque = new CodeSecret("8888");
        codeSecretBloque.nbEssaiErreur = 3;
        return codeSecretBloque;
    }

    public static CodeSecret creerCodeValide() {
        return new CodeSecret("8888");
    }

    public static CodeSecret creerCodeInvalide() {
        return new CodeSecret("1234");
    }
}
