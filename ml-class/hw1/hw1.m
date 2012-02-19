%% =========== Assignment 1: Least-Squares Regression =============
%   
%  Implements least squares estimators for polynomial regression
%  
%  Author: Benjamin Yu 
%  Student #: 996809912

fprintf('Loading and calculating estimators...\n');
load('a1TrainingData.mat');
load('a1TestingData.mat');
plot(x,y,'*b');
hold on;

m = size(x,1);      % Number of examples
n = size(xTest,1);
best_deg = 0;
max_deg = 10;       % Maximum degree of polynomial we'll search for
error = zeros(max_deg); % Errors
test_error = zeros(max_deg);
min_error = Inf;

x = [ones(m,1) x]; % Add ones for offset term
xTest = [ones(n,1) xTest];

for i = 1:max_deg
    x(:,i+1) = x(:,2).^ (i); % Generate higher-order polynomial terms
    xTest(:,i+1) = xTest(:,2).^ (i);
    temp_w = polynomialRegression(i,x,y);           % Calculate weights
    error(i) = norm(y - evalPolynomial(temp_w,x));  % Error
    test_error(i) = norm(yTest - evalPolynomial(temp_w,xTest));
    if (error(i) < min_error)                          % Compare to min Error
        min_error = error(i);
        best_deg = i;        
        best_w = polynomialRegression(best_deg,x(:,1:best_deg),y);    
        best_w = flipud(best_w); 
    end
end
plot (-2.1:0.1:2.1,polyval(best_w,-2.1:0.1:2.1));
hold off;

pause;

fprintf('Plotting residual errors...\n');
plot (1:max_deg,error);     % Plot Residual errors

pause

hold on;
fprintf('Plotting test errors...\n');
plot (1:max_deg,test_error);     % Plot Test errors
hold off;




