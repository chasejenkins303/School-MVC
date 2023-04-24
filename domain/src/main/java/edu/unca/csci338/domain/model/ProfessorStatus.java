package edu.unca.csci338.domain.model;


public enum ProfessorStatus {

	Teaching("teaching"),
    Sabaatical("sabaatical"),
    NoLongerTeaching("no longer teaching");

    @SuppressWarnings("unused")
    private final String value;

    ProfessorStatus(String status) {
		// TODO Auto-generated constructor stub
    	value = status;
	}
  
    public String toString() {
        switch (this) {
            case Teaching:
                return "teaching";
            case Sabaatical:
                return "sabaatical";
            case NoLongerTeaching:
                return "no longer teaching";
        }
		return "error";
    }

    public static ProfessorStatus fromString(String name) {
        switch (name) {
            case "teaching":
                return ProfessorStatus.Teaching;
            case "sabaatical":
                return ProfessorStatus.Sabaatical;
            case "no longer teaching":
                return ProfessorStatus.NoLongerTeaching;
        }
		return null;
    }
}

