SUMMARY = "A console development image with some C/C++ dev tools"
HOMEPAGE = "http://www.jumpnowtek.com"
LICENSE = "MIT"

IMAGE_FEATURES += "package-management"
IMAGE_LINGUAS = "en-us"

inherit core-image

CORE_OS = " \
    busybox-hwclock \
    openssh openssh-keygen openssh-sftp-server \
    term-prompt \
    tzdata \
 "

# Custom kernel modules built out of tree
KERNEL_MODULES_OOT = " \
    omap3-pwm \
    omap3-mux \
    omap3-irqlat \
    hrt-test \
    udelay-test \
 "

KERNEL_EXTRA_INSTALL = " \
    kernel-modules \
 "

WIFI_SUPPORT = " \
    crda \
    iw \
    linux-firmware-rtl8192ce \
    linux-firmware-rtl8192cu \
    linux-firmware-sd8686 \
    linux-firmware-sd8787 \
    linux-firmware-wl12xx \
    wpa-supplicant \
 "

DEV_SDK_INSTALL = " \
    binutils \
    binutils-symlinks \
    coreutils \
    cpp \
    cpp-symlinks \
    diffutils \
    file \
    gcc \
    gcc-symlinks \
    gdb \
    g++ \
    g++-symlinks \
    gettext \
    git \
    ldd \
    libstdc++ \
    libstdc++-dev \
    libtool \
    make \
    perl-modules \
    pkgconfig \
 "

DEV_EXTRAS = " \
    ntp \
    ntp-tickadj \
 "

PYTHON_SUPPORT = " \
    python-core \
    python-fcntl \
    python-subprocess \
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
    nano \
    procps \
    sysfsutils \
    tcpdump \
    unzip \
    wget \
    zip \
 "

IMAGE_INSTALL += " \
    ${CORE_OS} \
    ${DEV_SDK_INSTALL} \
    ${DEV_EXTRAS} \
    ${EXTRA_TOOLS_INSTALL} \
    ${KERNEL_EXTRA_INSTALL} \
    ${PYTHON_SUPPORT} \
    ${WIFI_SUPPORT} \
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

export IMAGE_BASENAME = "console-image"

