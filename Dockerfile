FROM openliberty/raspberrypi:latest

LABEL maintainer="Warkdev" vendor="Private" github="https://github.com/Warkdev/account-service"

COPY --chown=1001:0 src/main/liberty/config/ /config/
COPY --chown=1001:0 target/*.war /config/apps/

# Add interim fixes (optional)
#COPY --chown=1001:0  interim-fixes /opt/ol/fixes/

# Default setting for the verbose option
ARG VERBOSE=false

# This script will add the requested XML snippets, grow image to be fit-for-purpose and apply interim fixes
RUN configure.sh