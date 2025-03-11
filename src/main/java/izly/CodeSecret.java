package izly;

import java.util.Random;

public class CodeSecret {
    private boolean codeRevele;
    private String code;
    private int nbEssaisFaux;
    private final int nbEssaisMax = 3;

    public static CodeSecret createCode(Random random) {
        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            codeBuilder.append(random.nextInt(10));
        }
        return new CodeSecret(codeBuilder.toString());
    }

    private CodeSecret(String code) {
        this.code = code;
    }

    public boolean verifierCode(String code) throws CodeBloqueException {
        if(isBlocked()) {
            throw new CodeBloqueException();
        }
        if (!this.code.equals(code)) {
            nbEssaisFaux++;
            return false;
        }
        nbEssaisFaux=0;
        return true;
    }

    public boolean isBlocked() {
        return nbEssaisFaux>=nbEssaisMax;
    }

    public String revelerCode() {
        if (!codeRevele) {
            codeRevele=true;
            return code;
        }
        return "xxxx";
    }
}
