
Pod::Spec.new do |s|
  s.name         = "RNImqaReactNativeModule"
  s.version      = "1.0.0"
  s.summary      = "RNImqaReactNativeModule"
  s.description  = <<-DESC
                  RNImqaReactNativeModule
                   DESC
  s.homepage     = "https://github.com/onycom-imqa/IMQA-RN-Module"
  s.license      = "AGPL-3.0"
  s.author       = { "author" => "admin@imqa.io" }
  s.platform     = :ios, "11.0"
  s.source       = { :git => "https://github.com/onycom-imqa/IMQA-RN-Module.git", :tag => "develop" }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true
  s.dependency "React"
#   s.dependency "IMQAMPMAgent", '3.23.0'
#   s.dependency "IMQACrashAgent", '3.13.0'
  #s.dependency "others"

end
