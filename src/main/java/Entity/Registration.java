package Entity;

public class Registration {
        // Private fields for the Registration table
        private int regId;
        private String name;
        private String email;
        private String mobile;
        private String gender;
        private String dob;
        private String image;
        private int clubId;
        private int sportsId;
        private String sportsName;
        private String clubName;
        private Double fees;
        // Getter and Setter methods for regId
        public int getRegId() {
            return regId;
        }

        public void setRegId(int regId) {
            this.regId = regId;
        }

        // Getter and Setter methods for name
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        // Getter and Setter methods for email
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        // Getter and Setter methods for mobile
        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        // Getter and Setter methods for gender
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        // Getter and Setter methods for dob
        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        // Getter and Setter methods for image
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        // Getter and Setter methods for clubId
        public int getClubId() {
            return clubId;
        }

        public void setClubId(int clubId) {
            this.clubId = clubId;
        }

        // Getter and Setter methods for sportsId
        public int getSportsId() {
            return sportsId;
        }

        public void setSportsId(int sportsId) {
            this.sportsId = sportsId;
        }

        public String getSportsName(String sportsName) {
        return this.sportsName;
    }

    public void setSportsName(String sportsName) {
        this.sportsName = sportsName;
    }
    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }
    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    }

