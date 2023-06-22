FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1
RUN apt-get update && \
    apt-get install -y \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libgl1-mesa-glx libgtk-3-0 openjfx libgl1-mesa-dri libgl1-mesa-dev libcanberra-gtk-module libcanberra-gtk3-module default-jdk
ENV DISPLAY=host.docker.internal:0
WORKDIR /app
ADD . /app
CMD sbt -Djava.awt.headless=false run
#CMD sbt -Djava.awt.headless=false -Dawt.useSystemAAFontSettings=lcd -Dsun.java2d.xrender=true run

# make sure X Server (XMing) is running in the background and acl is disabled in XMing configurations
# build with: docker build . -t yugioh:v1
# run with: docker run -int -rm -e DISPLAY=host.docker.internal:0 yugioh:v1
# (no it doesnt work with this run) or docker run -rm -int yugioh:v1