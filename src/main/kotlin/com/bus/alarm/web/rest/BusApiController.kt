package com.bus.alarm.web.rest

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponents
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

@RestController
class BusApiController {

    val URL : String = "http://openapi.tago.go.kr/openapi/service/BusLcInfoInqireService/getRouteAcctoBusLcList"

    var busLocation : String = "http://openapi.gbis.go.kr/ws/rest/buslocationservice"

    val arriveBusStop : String = "http://openapi.tago.go.kr/openapi/service/ArvlInfoInqireService/getSttnAcctoArvlPrearngeInfoList\n"

    @GetMapping("/bus")
    fun test(): ResponseEntity<String> {

        val cityCode : Int = 25
        val routeId : String = "DJB30300052"

        var header : HttpHeaders = HttpHeaders()
        var entity : HttpEntity<BusApiRequestForm> = HttpEntity(header)

        val uri : String = URL + "?ServiceKey=vTPksRw%2BL0BAX5kwRcSqXfLzVj5kIUjpUF7XOfMv8VzMVgTnafyOU1%2F6lMq4qmwnBBh%2FF0rJ78D%2Fl%2B8gVXN1rQ%3D%3D" + "&cityCode=" + cityCode + "&routeId=" + routeId

        val result : ResponseEntity<Map<String,Any>> = RestTemplate().exchange(uri,HttpMethod.GET,entity, HashMap<String,Any>())
        print(result.to(String))

        return ResponseEntity.ok(result.toString())
    }


    @GetMapping("/bus-loc")
    fun busLocation(): ResponseEntity<String> {

        val cityCode : Int = 25
        val routeId : String = "241221003"

        var header : HttpHeaders = HttpHeaders()
        var entity : HttpEntity<BusApiRequestForm> = HttpEntity(header)

        val uri : String = busLocation + "?ServiceKey=vTPksRw%2BL0BAX5kwRcSqXfLzVj5kIUjpUF7XOfMv8VzMVgTnafyOU1%2F6lMq4qmwnBBh%2FF0rJ78D%2Fl%2B8gVXN1rQ%3D%3D" + "&routeId=" + routeId

        val result : ResponseEntity<Map<String,Any>> = RestTemplate().exchange(uri,HttpMethod.GET,entity, HashMap<String,Any>())
        print(result.to(String))

        return ResponseEntity.ok(result.toString())
    }

    @GetMapping("/bus-arrive")
    fun arriveBusStop(): ResponseEntity<String> {

        val cityCode : Int = 25
        val nodeId : String = "05053"

        var header : HttpHeaders = HttpHeaders()
        var entity : HttpEntity<BusApiRequestForm> = HttpEntity(header)

        val uri : String = arriveBusStop + "?ServiceKey=vTPksRw%2BL0BAX5kwRcSqXfLzVj5kIUjpUF7XOfMv8VzMVgTnafyOU1%2F6lMq4qmwnBBh%2FF0rJ78D%2Fl%2B8gVXN1rQ%3D%3D" + "&nodeId=" + nodeId

        val result : ResponseEntity<Map<String,Any>> = RestTemplate().exchange(uri,HttpMethod.GET,entity, HashMap<String,Any>())
        print(result.to(String))

        return ResponseEntity.ok(result.toString())
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BusApiRequestForm(
        val cityCode : Int,
        val routeId : String)



    @JsonIgnoreProperties(ignoreUnknown = true)
    data class BusApiResponse(
        @JsonProperty("routenm")
        val routenm : Int,
        @JsonProperty("gpslati")
        val gpslati : Double,
        @JsonProperty("gpslong")
        val gpslong : Double,
        @JsonProperty("nodeord")
        val nodeord : Int,
        @JsonProperty("nodenm")
        val nodenm : String,
        @JsonProperty("nodeid")
        val nodeid : String,
        @JsonProperty("routetp")
        val routetp : String,
        @JsonProperty("vehicleno")
        val vehicleno : String)

}