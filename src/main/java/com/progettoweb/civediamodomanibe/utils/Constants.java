package com.progettoweb.civediamodomanibe.utils;

public class Constants {
    private Constants() {
    }

    public static class Role {
        private Role() {
        }

        public static final Long AMMINISTRATORE = 1L;
        public static final Long NORMAL = 2L;
    }


    public static class UserStatus {
        public static final String ABILITATO = "ABILITATO";
        public static final String DISABILITATO = "DISABILITATO";

        private UserStatus() {
        }
    }

    public static class Boolean {
        public static final Long TRUE = 1L;
        public static final Long FALSE = 0L;

        private Boolean() {
        }
    }

    public enum SaveErrorTemplate {
        ENTITY_ALREADY_EXIST("Impossibile salvare: un oggetto %s è già esistente per i parametri: %s."),
        ENTITY_MISSING_DATA("Impossibile salvare: valorizzare tutti i campi obbligatori!"),
        ENTITY_DATA_DISCREPANCY("Impossibile salvare: correggere i dati incongruenti."),
        ENTITY_SAVE_RESTRICTED("L'oggetto %s non può essere salvato."),
        ENTITY_PERMISSION_DENIED("Non sei autorizzato a salvare quest'oggetto %s.");

        private final String error_message;
        SaveErrorTemplate(String message) {
            this.error_message = message;
        }
        public String getMessage() {
            return this.error_message;
        }
    }

    public enum UpdateErrorTemplate {
        ENTITY_ALREADY_EXIST("Impossibile modificare: un oggetto %s è già esistente per i parametri: %s."),
        ENTITY_MISSING_DATA("Impossibile modificare: valorizzare tutti i campi obbligatori!"),
        ENTITY_DATA_DISCREPANCY("Impossibile modificare: correggere i dati incongruenti."),
        ENTITY_UPDATE_RESTRICTED("L'oggetto %s non può essere modificato."),
        ENTITY_PERMISSION_DENIED("Non sei autorizzato a modificare quest'oggetto %s.");

        private final String error_message;
        UpdateErrorTemplate(String message) {
            this.error_message = message;
        }
        public String getMessage() {
            return this.error_message;
        }
    }

    public enum DeleteErrorTemplate {
        ENTITY_ALREADY_DELETED("L'entità %s con id: %s è già stata eliminata."),
        ENTITY_HAS_CHILDREN("L'entità %s con id: %s ha associate altre relazioni."),
        ENTITY_DELETE_RESTRICTED("L'entità %s con id: %s non può essere eliminata."),
        ENTITY_PERMISSION_DENIED("Non sei autorizzato a eliminare quest'oggetto %s.");

        private final String error_message;
        DeleteErrorTemplate(String message) {
            this.error_message = message;
        }
        public String getMessage() {
            return this.error_message;
        }
    }

    public enum SearchErrorTemplate {
        ENTITY_NOT_FOUND("%s non trovato per i parametri %s");

        private final String error_message;
        SearchErrorTemplate(String message) {
            this.error_message = message;
        }
        public String getMessage() {
            return this.error_message;
        }
    }
}
