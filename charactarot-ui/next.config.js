/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  images: { domains: ['localhost', 'gatherer.wizards.com'] },
  httpAgentOptions: {
    rejectUnauthorized: false
  },
  async rewrites() {
    return [
      {
        source: '/api/v1/:path*',
        destination: 'http://localhost:8080/api/v1/:path*',
      },
    ]
  }
}

module.exports = nextConfig
