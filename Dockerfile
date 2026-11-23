FROM maven:3.9-eclipse-temurin-25

RUN apt-get update \
 && apt-get install -y --no-install-recommends wget gnupg ca-certificates \
 && wget -qO- https://dl-ssl.google.com/linux/linux_signing_key.pub | gpg --dearmor -o /usr/share/keyrings/google-chrome.gpg \
 && echo "deb [signed-by=/usr/share/keyrings/google-chrome.gpg] https://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
 && apt-get update \
 && apt-get install -y --no-install-recommends google-chrome-stable \
 && rm -rf /var/lib/apt/lists/*

WORKDIR /tests

COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src

ENTRYPOINT ["mvn", "-B", "-Dselenide.headless=true", "-Dchromeoptions.args=--no-sandbox"]
CMD ["test"]