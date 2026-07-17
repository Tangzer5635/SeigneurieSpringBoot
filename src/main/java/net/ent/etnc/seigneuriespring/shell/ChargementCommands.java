package net.ent.etnc.seigneuriespring.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jline.terminal.Terminal;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.InvalidJobParametersException;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.launch.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.JobRestartException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ChargementCommands {

    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    @NonNull
    private final Terminal terminal;

    @NonNull
    private final JobOperator jobOperator;

    @NonNull
    private final Job initialisationJob;

    @ShellMethod(key = "init", value = "lancement de l'initialisation")
    public void init() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("start", System.currentTimeMillis())
                    .toJobParameters();
            JobExecution execution = jobOperator.start(initialisationJob, jobParameters);
            terminal.writer().println(GREEN + "Initialisation terminée : " + execution.getStatus() + RESET);
        } catch (JobInstanceAlreadyCompleteException | InvalidJobParametersException |
                 JobExecutionAlreadyRunningException | JobRestartException e) {
            terminal.writer().println(RED + "Échec de l'initialisation : " + e.getMessage() + RESET);
        }
    }
}