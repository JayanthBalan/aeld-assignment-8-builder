# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "Unknown"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com:cu-ecen-aeld/assignments-3-and-later-JayanthBalan.git;protocol=ssh;branch=main"

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "d32b337bdb528193a142e3483bd1f5769fe78a13"

S = "${WORKDIR}/git/aesd-char-driver"

FILES:${PN} += "${bindir}/aesdchar_load ${bindir}/aesdchar_unload"

inherit module update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "aeld-assignments-start-stop"
INITSCRIPT_PARAMS:${PN} = "defaults 95"

FILES:${PN} += "${sysconfdir}/init.d/aeld-assignments-start-stop"

SRC_URI += "file://aeld-assignments-start-stop"

EXTRA_OEMAKE += "KERNELDIR=${STAGING_KERNEL_DIR}"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aeld-assignments-start-stop ${D}${sysconfdir}/init.d/aeld-assignments-start-stop

    install -d ${D}${bindir}
	install -m 0755 ${S}/aesdchar_unload ${D}${bindir}/aesdchar_unload
    install -m 0755 ${S}/aesdchar_load ${D}${bindir}/aesdchar_load
}
