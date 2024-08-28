package Entity;

public class Sports {
        // Private fields for the Sports table
        private int sportsId;
        private String sportsName;
        private int clubId;
        private double fees;

        // Getter and Setter methods for sportsId
        public int getSportsId() {
            return sportsId;
        }

        public void setSportsId(int sportsId) {
            this.sportsId = sportsId;
        }

        // Getter and Setter methods for sportsName
        public String getSportsName() {
            return sportsName;
        }

        public void setSportsName(String sportsName) {
            this.sportsName = sportsName;
        }

        // Getter and Setter methods for clubId
        public int getClubId() {
            return clubId;
        }

        public void setClubId(int clubId) {
            this.clubId = clubId;
        }

        // Getter and Setter methods for fees
        public double getFees() {
            return fees;
        }

        public void setFees(double fees) {
            this.fees = fees;
        }
    }


