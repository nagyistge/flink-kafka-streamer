package org.apache.flink.quickstart

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka._
import java.util.Properties
import org.apache.flink.streaming.util.serialization.SimpleStringSchema
/**
 * Skeleton for a Flink Job.
 *
 * For a full example of a Flink Job, see the WordCountJob.scala file in the
 * same package/directory or have a look at the website.
 *
 * You can also generate a .jar file that you can submit on your Flink
 * cluster. Just type
 * {{{
 *   mvn clean package
 * }}}
 * in the projects root directory. You will find the jar in
 * target/flink-quickstart-0.1-SNAPSHOT-Sample.jar
 *
 */
object Job {
  def main(args: Array[String]) {
    // set up the execution environment
    val env = StreamExecutionEnvironment.getExecutionEnvironment

    val host = args(0)
    val topics = args(1)
    val sinkTopics = "rewrite"
    val properties = new Properties();
    properties.setProperty("bootstrap.servers", host + ":9092")
    properties.setProperty("group.id", "test");

    val stream = env
      .addSource(new FlinkKafkaConsumer09[String](topics, new SimpleStringSchema, properties))
      .addSink(new FlinkKafkaProducer09(sinkTopics, new SimpleStringSchema, properties))

    // execute program
    env.execute("Flink Scala API Skeleton")
  }
}
