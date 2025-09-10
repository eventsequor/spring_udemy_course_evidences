package com.eventsequor.backend_crud_products.parallelcustom;

import io.qameta.allure.junitplatform.AllureJunitPlatform;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.discovery.DiscoverySelectors;
import org.junit.platform.launcher.*;
import org.junit.platform.launcher.core.*;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.PrintWriter;
import java.util.stream.Stream;

import static net.bytebuddy.matcher.ElementMatchers.named;


public class LauncherRunner {

    public static void main(String[] args) throws Exception {
        // Crear clase dinámica que reemplaza `sourceParameter()`


        LauncherDiscoveryRequest launcherDiscoveryRequest = launcher("ClassName1", "E", "F");
        LauncherDiscoveryRequest launcherDiscoveryRequest2 = launcher("ClassName2", "G", "H");

        Launcher launcher = LauncherFactory.create();

        // Listener que genera los archivos para Allure
        AllureJunitPlatform allureListener = new AllureJunitPlatform();
        SummaryGeneratingListener summaryListener = new SummaryGeneratingListener();

        launcher.registerTestExecutionListeners(
                allureListener,
                summaryListener
        );

        SummaryGeneratingListener listener = new SummaryGeneratingListener();
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(launcherDiscoveryRequest);
        System.out.println("\n\n Other test from here");
        launcher.execute(launcherDiscoveryRequest2);

        PrintWriter consoleOutput = new PrintWriter(System.out);
        listener.getSummary().printTo(consoleOutput);
        System.out.println("Here");
    }

    public static LauncherDiscoveryRequest launcher(String className, String letterA, String letterB) {
        Class<? extends TestClass1> generatedTestClass = new ByteBuddy()
                .subclass(TestClass1.class)
                .name("com.eventsequor.backend_crud_products.parallelcustom." + className)
                .method(named("sourceParameter"))
                .intercept(MethodDelegation.to(new Object() {
                    public Stream<Arguments> sourceParameter() {
                        return Stream.of(letterA, letterB).map(Arguments::of);
                    }
                }))
                .make()
                .load(TestClass1.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();


        return LauncherDiscoveryRequestBuilder.request()
                .selectors(DiscoverySelectors.selectClass(generatedTestClass))
                .build();
    }

    @Test
    void runCustomLauncher() throws Exception {
        LauncherRunner.main(new String[]{});
    }
}

