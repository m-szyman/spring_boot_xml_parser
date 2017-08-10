#XML analysis API
#####API server which can analyse the content of xml files_

_Example of using SAX API to parse XML content_

**Dockerfile**

To build the Docker image use the command line like this:

`$ ./mvnw install dockerfile:build`

To run a docker image use the command line like this:

`$ docker run -p 8080:8080 -t sayenpl/xml-analysis-api`

The application is then available on `http://localhost:8080/analyze`



**HTTP request example**
~~~~
curl -i -X POST \
   -H "Content-Type:application/json" \
   -d \
'{ \
   "url": "https://sayen.pl/xml-analysis-api/3dprinting-posts.xml" \
}' \
 'http://localhost:8080/analyze/'
 ~~~~
 
 Find other files on archive site https://archive.org/details/stackexchange
 
 **Response example**
~~~~
{
    "analyseDate": "2017-08-10T18:21:32.445",
    "details": {
        "firstPost": "2016-01-12T18:45:19.963",
        "lastPost": "2016-03-04T13:30:22.41",
        "totalPosts": 545,
        "questions": 185,
        "answers": 360,
        "totalAcceptedPosts": 102,
        "avgScore": 3,
        "minScore": -5,
        "maxScore": 18
    }
}
~~~~