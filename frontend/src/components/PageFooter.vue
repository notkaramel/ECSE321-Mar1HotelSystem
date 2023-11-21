<template>
  <fwb-footer>
    <fwb-footer-copyright by="Mar-1 Hotel." href="https://example.com/" copyright-message="All Rights Reserved." />
    <fwb-footer-link-group>
      <fwb-footer-link href="#">
        About
      </fwb-footer-link>
      <fwb-footer-link href="#">
        Privacy Policy
      </fwb-footer-link>
      <fwb-footer-link href="#">
        Licensing
      </fwb-footer-link>
    </fwb-footer-link-group>
  </fwb-footer>
</template>
  
<script setup lang="ts">
import {
  FwbFooter,
  FwbFooterCopyright,
  FwbFooterLink,
  FwbFooterLinkGroup,
} from 'flowbite-vue'
</script>

<style scoped lang="postcss">
</style>