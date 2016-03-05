FROM zavalit/flink:1.1-SNAPSHOT

ENV PROGRAMM_VER 0.1
ENV PROGRAMM_JAR flink-scala-project-$PROGRAMM_VER.jar
WORKDIR /root
ADD target/$PROGRAMM_JAR .
ADD start .
RUN chmod +x start
CMD ./start
