package elastest.epm.adapter.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PreDestroy;

@SpringBootApplication
public class EpmAdapterDockerApplication implements CommandLineRunner {

    @Autowired private Runner runner;

    private static String adapterId = "";
    private static String epmIp = "";

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
        runner.init();
    }

    @PreDestroy
    public void destroy() {
        Utils utils = new Utils();
        utils.unregister(adapterId, epmIp);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }
    }

	public static void main(String[] args) throws InterruptedException {
        Utils utils = new Utils();
        if(args.length >= 3 && args[0].equals("--register-pop")) {
            adapterId = utils.register(args[1], args[2]);
            epmIp = args[1];
        }
        else if(args.length == 1 && args[0].equals("--register-pop")){
            adapterId = utils.register("localhost", "localhost");
            epmIp = "localhost";
        }
		SpringApplication.run(EpmAdapterDockerApplication.class, args);
	}
}
