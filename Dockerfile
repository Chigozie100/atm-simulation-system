FROM openjdk:18
COPY out/artifacts/atm_simulation_system_jar/atm-simulation-system.jar atm-simulation-system.jar
ENTRYPOINT ["java","-jar","atm-simulation-system.jar"]
EXPOSE 8080