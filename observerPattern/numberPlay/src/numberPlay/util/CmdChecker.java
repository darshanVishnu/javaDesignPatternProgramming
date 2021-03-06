package numberPlay.util;

import numberPlay.util.ValidatorUtil;
import java.util.Arrays;
/*
Used for checking input command line arguments
 */
public class CmdChecker {
    private String[] args;

    private static class ValidatorFetcher {
        /*
        validateArgslength check for the args lenght
        @return Validator
        @params CmdChecker
         */
       public static final int REQUIRED_NUMBER_OF_ARGS = 6;

        public static Validator validateArgslength(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if ((checklength.args.length != REQUIRED_NUMBER_OF_ARGS) ||
                            (checklength.args[0].equals("${inputNumStream}")) ||
                            (checklength.args[1].equals("${runAvgWindowSize}")) ||
                            (checklength.args[2].equals("${runAvgOutFile}")) ||
                            (checklength.args[3].equals("${k}")) ||
                            (checklength.args[4].equals("${topKNumOutFile}")) ||
                            (checklength.args[5].equals("${numPeaksOutFile}"))) {
                        System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                        System.exit(0);
                    }
                }
            };
        }

        public static Validator validateInputFile(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (checklength.args[0].equals("") || checklength.args[0] == null) {
                        System.err.printf("Error: Input file is missing.Please give path of the inputfile");
                        System.exit(0);
                    }
                }
            };
        }

        public static Validator validateWindowSizeAverageK(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (checklength.args[1] != null) {
                        try {
                            Integer dk= Integer.parseInt(checklength.args[1]);
                            if(dk <=0){
                                System.err.printf("Error: AverageK Window Size must be greater than 0.Please give +ve integer value");
                                System.exit(0);
                            }
                        } catch (NumberFormatException error) {
                            System.err.printf("Error: AverageK Window Size  is missing.Please give +ve integer value  greater than 0");
                            System.exit(0);
                        }
                    }
                    else{
                        System.err.printf("Error: AverageK Window Size  is missing.Please give +ve integer value  greater than 0");
                        System.exit(0);
                    }
                }
            };
        }


        public static Validator validateWindowSizeTopK(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (checklength.args[3] != null) {
                        try {
                            Integer tk= Integer.parseInt(checklength.args[3]);
                            if(tk <=0){
                                System.err.printf("Error: TopK Window Size must be greater than 0.Please give +ve integer value ");
                                System.exit(0);
                            }
                        } catch (NumberFormatException error) {
                            System.err.printf("Error: TopK Window Size  is missing.Please give +ve integer value  greater than 0");
                            System.exit(0);
                        }
                    }
                    else{
                        System.err.printf("Error: TopK Window Size  is missing.Please give +ve integer value greater than 0");
                        System.exit(0);
                    }
                }
            };
        }
    }

    /*
    Constructor of class CmdChecker
    used to valiadate cmd agrs
    @exection when failed to validate error
     */
    public CmdChecker(String[] args) throws Exception {
        this.args = args;
        ValidatorUtil.validate("failed", ValidatorFetcher.validateArgslength(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateInputFile(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateWindowSizeAverageK(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateWindowSizeTopK(this));

    }
    @Override
    public String toString() {
        return String.format("CmdChecker[args:%s]",Arrays.toString(args));
    }

    @Override
    public int hashCode() {
        return (args.hashCode()) *13;
    }
}
