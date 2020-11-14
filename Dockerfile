# OpenLiberty phase
FROM openliberty/raspberrypi:latest

LABEL maintainer="Warkdev" vendor="Private" github="https://github.com/Warkdev/account-service"

COPY --chown=1001:0 src/main/liberty/config/ /config/
USER root
RUN mkdir -p /apps
RUN chown 1001:0 /apps
USER 1001
COPY --chown=1001:0 target/*.war /apps
COPY --chown=1001:0 target/liberty/wlp/usr/shared/resources/mariadb-java-client-2.7.0.jar  /opt/ol/wlp/usr/shared/resources/

# Add interim fixes (optional)
#COPY --chown=1001:0  interim-fixes /opt/ol/fixes/

# Default setting for the verbose option
ARG VERBOSE=false

# This script will add the requested XML snippets, grow image to be fit-for-purpose and apply interim fixes
RUN configure.sh