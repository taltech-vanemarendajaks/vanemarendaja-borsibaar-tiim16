FROM eclipse-temurin:21-jdk AS base
WORKDIR /build
COPY --chmod=0755 mvnw mvnw
COPY .mvn/ .mvn/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2 \
    ./mvnw dependency:go-offline -DskipTests

FROM base AS test
WORKDIR /build
COPY ./pom.xml pom.xml
COPY ./src src/
RUN --mount=type=bind,source=pom.xml,target=pom.xml \
    --mount=type=cache,target=/root/.m2

FROM base
WORKDIR /app
COPY ./pom.xml pom.xml
COPY ./src src/
COPY --from=base /build/mvnw mvnw
COPY --from=base /build/.mvn .mvn
CMD ["./mvnw", "spring-boot:run"]
EXPOSE 8080
