FROM registry.access.redhat.com/ubi9/openjdk-21-runtime:1.20

LABEL BASE_IMAGE="registry.access.redhat.com/ubi9/openjdk-21-runtime:1.20"
LABEL JAVA_VERSION="21"
LABEL authors="Makhai"

ENV LANGUAGE='fr_FR:fr'
ENV TZ='Europe/Paris'

COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/app/ /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185

ENV JAVA_OPTS_APPEND="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager -XX:TieredStopAtLevel=1 -noverify -XX:+AlwaysPreTouch -XX:+UseNUMA -Xlog:gc*,safepoint=debug:file=/tmp/gc.log.%p:time,uptime:filecount=5,filesize=50M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"
ENV GC_CONTAINER_OPTIONS="-XX:+UseShenandoahGC"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]