public class Concatenation {
        final String subj;
        final String obj;
        final String verb;


    public Concatenation(String subj, String obj, String verb) {
        this.subj = subj;
        this.obj = obj;
        this.verb = verb;
    }

    private String build() {
        return String.format("%s %s %s", subj, verb, obj);
    }

    @Override
    public String toString() {
        return build();
    }
}
