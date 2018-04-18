node('docker'){
    stage "Container Prep"
        def mycontainer = docker.image('elastest/ci-docker-siblings:latest')
        mycontainer.pull() // make sure we have the latest available from Docker Hub
        echo("the node is up")

        mycontainer.inside("-u jenkins -v /var/run/docker.sock:/var/run/docker.sock:rw") {

            git 'https://github.com/mpauls/epm-adapter-docker'

            stage "Package"
                echo ("Compiling EPM Docker Adapter ...")
                sh './gradlew clean build -x test'

            stage "Unit tests"
                echo ("Starting unit tests...")
                //sh './gradlew test jacocoTestReport'
                sh './gradlew test'

            stage "Build image - Package"
                echo ("Building docker image...")
                sh 'cp build/libs/epm-adapter-docker-*.jar docker/epm-adapter-docker/epm-adapter-docker.jar'
                sh 'docker build --build-arg GIT_COMMIT=$(git rev-parse HEAD) --build-arg COMMIT_DATE=$(git log -1 --format=%cd --date=format:%Y-%m-%dT%H:%M:%S) . -t elastest/epm-adapter-docker:latest'
                def myimage = docker.image('elastest/epm-adapter-docker:latest')

            stage "Run image"
                echo "Run the image..."
                myimage.run()

            stage "Integration tests"
                echo ("Starting integration tests...")
                echo ("No integration tests yet")

            stage "Publish"
                echo ("Publishing as all tests succeeded...")
                withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'elastestci-dockerhub',
                                  usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh 'docker login -u "$USERNAME" -p "$PASSWORD"'
                    //myimage.push()
            }
    }
}
