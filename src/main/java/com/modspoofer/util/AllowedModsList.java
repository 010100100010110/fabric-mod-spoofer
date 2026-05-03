package com.modspoofer.util;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

public class AllowedModsList {
    private static final Set<String> ALLOWED_MODS = new HashSet<>();

    static {
        String encodedMods = "Y2ljYWRhLWxpYiwwLjE0LjMrMS4yMS45LTEuMjEuMTAKY2xvdGgtY29uZmlnLDIxLjExLjE1My1mYWJyaWMKZHluYW1pY2Nyb3NzaGFpciw5LjExKzEuMjEuMTEtZmFicmljCmZhYnJpYy1hcGksMS40MS4zKzEuMjEuMQpmYWJyaWMtbGFuZ3VhZ2Usa290bGluLDEuMTMuMTEra290bGluLjIuMy4yMQppcmlzLWZhYnJpYywxLjEwLjcrbWMxLjIxLjExCk1vdXNlVHdlYWtzLWZhYnJpYyxtYzEuMjEuMTEtMi4zMAp1d28tbGliLDAuMTMuMCsxLjIxLjExCnJlZXNlcy1zb2RpdW0tb3B0aW9ucyxmYWJyaWMsMi4wLjMrbWMxLjIxLjExClNlYXJjaGFibGVzLGZhYnJpYywxLjIxLjExLTEuMC40CnNob3ctbWUteW91ci1za2luLDIuMC4yKzEuMjEuMTEKc2h1bGtlcmJveHRvb2x0aXAsZmFicmljLDUuMi4xNisxLjIxLjExCnNraW5sYXllcnMzZCxmYWJyaWMsMS4xMS4xLW1jMS4yMS4xMQpza2luc2h1ZmZsZSwyLjEwLjIrMS4yMS4xMS1mYWJyaWMKc29kaXVtLGZhYnJpYywwLjguNyttYzEuMjEuMTEKdm9pY2VjaGF0LGZhYnJpYywxLjIxLjExLTIuNi4xNgp3b3JsZGVkaXQtbW9kLDcuNC4yLTEuMjEuMTE=";
        String decodedMods = new String(Base64.getDecoder().decode(encodedMods));
        String[] mods = decodedMods.split("\n");
        for (String mod : mods) {
            if (!mod.trim().isEmpty()) {
                ALLOWED_MODS.add(mod.trim());
            }
        }
    }

    public static Set<String> getAllowedMods() {
        return new HashSet<>(ALLOWED_MODS);
    }

    public static boolean isModAllowed(String modId) {
        return ALLOWED_MODS.stream().anyMatch(mod -> mod.toLowerCase().contains(modId.toLowerCase()));
    }

    public static String getSpoofdModList() {
        return String.join(",", ALLOWED_MODS);
    }
}
