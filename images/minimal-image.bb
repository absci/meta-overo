SUMMARY = "A minimal image"
HOMEPAGE = "http://www.jumpnowtek.com"
LICENSE = "MIT"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
    openssh openssh-keygen openssh-sftp-server \
    term-prompt \
    tzdata \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
 "

EXTRA_TOOLS_INSTALL = " \
    bzip2 \
    devmem2 \
    ethtool \
    findutils \
    i2c-tools \
    iftop \
    iperf \
    htop \
    less \
    memtester \
    netcat \
    procps \
    sysfsutils \
    tcpdump \
    unzip \
    wget \
    zip \
 "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${KERNEL_EXTRA_INSTALL} \
 "

set_local_timezone() {
    ln -sf /usr/share/zoneinfo/EST5EDT ${IMAGE_ROOTFS}/etc/localtime
}

disable_bootlogd() {
    echo BOOTLOGD_ENABLE=no > ${IMAGE_ROOTFS}/etc/default/bootlogd
}

ROOTFS_POSTPROCESS_COMMAND += " \
    set_local_timezone ; \
    disable_bootlogd ; \
 "

export IMAGE_BASENAME = "minimal-image"
