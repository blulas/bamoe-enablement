/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.decisiontable.quarkus.ruleunit;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
public class RestQueryTest {

    private static final String JSON_PAYLOAD =
            "{\n" +
                    "  \"maxAmount\":5000,\n" +
                    "  \"loanApplications\":[\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10001\",\n" +
                    "      \"amount\":2000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":45,\n" +
                    "        \"name\":\"John\"\n" +
                    "      }\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10002\",\n" +
                    "      \"amount\":5000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":25,\n" +
                    "        \"name\":\"Paul\"\n" +
                    "      }\n" +
                    "    },\n" +
                    "    {\n" +
                    "      \"id\":\"ABC10015\",\n" +
                    "      \"amount\":1000,\n" +
                    "      \"deposit\":100,\n" +
                    "      \"applicant\":{\n" +
                    "        \"age\":12,\n" +
                    "        \"name\":\"George\"\n" +
                    "      }\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}";

    @Test
    public void testApproved() {
        given()
                .body(JSON_PAYLOAD)
                .contentType(ContentType.JSON)
                .when()
                .post("/find-approved")
                .then()
                .statusCode(200)
                .body("id", hasItem("ABC10001"));
    }

    @Test
    public void testNotApproved() {
        given()
                .body(JSON_PAYLOAD)
                .contentType(ContentType.JSON)
                .when()
                .post("/find-not-approved-id-and-amount")
                .then()
                .statusCode(200)
                .body("$id", hasItems("ABC10002", "ABC10015"));
    }

    @Test
    public void testAllApplicationsAggregation() {
        given()
                .body(JSON_PAYLOAD)
                .contentType(ContentType.JSON)
                .when()
                .post("/find-all-application-amounts")
                .then()
                .statusCode(200)
                .body("amounts", hasItems(8000));
    }
}