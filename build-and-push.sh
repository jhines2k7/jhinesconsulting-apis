#!/usr/bin/env bash
./mvnw clean
./mvnw package
mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

VERSION=1.9.0

if [ "$ENV" = "dev" ] ; then
    docker build -t jhines2017/jhinesconsulting-apis:$VERSION-SNAPSHOT \
    -f Dockerfile-dev \
    --build-arg VERSION=$VERSION \
    --build-arg REVISION=-SNAPSHOT . \
    && docker login --username=$DOCKER_HUB_USER --password=$DOCKER_HUB_PASSWORD \
    && docker push jhines2017/jhinesconsulting-apis:$VERSION-SNAPSHOT &

    docker build -t jhines2017/jhinesconsulting-apis:$VERSION.RELEASE \
    -f Dockerfile-dev \
    --build-arg VERSION=$VERSION \
    --build-arg REVISION=.RELEASE . \
    && docker login --username=$DOCKER_HUB_USER --password=$DOCKER_HUB_PASSWORD \
    && docker push jhines2017/jhinesconsulting-apis:$VERSION.RELEASE &

    wait
else
    docker build -t jhines2017/jhinesconsulting-apis:$VERSION-SNAPSHOT \
    --build-arg VERSION=$VERSION \
    --build-arg REVISION=-SNAPSHOT . \
    && docker login --username=$DOCKER_HUB_USER --password=$DOCKER_HUB_PASSWORD \
    && docker push jhines2017/jhinesconsulting-apis:$VERSION-SNAPSHOT &

    docker build -t jhines2017/jhinesconsulting-apis:$VERSION.RELEASE \
        --build-arg VERSION=$VERSION \
        --build-arg REVISION=.RELEASE . \
    && docker login --username=$DOCKER_HUB_USER --password=$DOCKER_HUB_PASSWORD \
    && docker push jhines2017/jhinesconsulting-apis:$VERSION.RELEASE &

    wait
fi
