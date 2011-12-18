function [theta, J_history] = gradientDescentMulti(X, y, theta, alpha, num_iters)
%GRADIENTDESCENTMULTI Performs gradient descent to learn theta
%   theta = GRADIENTDESCENTMULTI(x, y, theta, alpha, num_iters) updates theta by
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);

for iter = 1:num_iters

    % ====================== YOUR CODE HERE ======================
    % Instructions: Perform a single gradient step on the parameter vector
    %               theta. 
    %
    % Hint: While debugging, it can be useful to print out the values
    %       of the cost function (computeCostMulti) and gradient here.
    %
    n = length(theta);
    temp_theta = theta; % Theta can't be updated since we want to update in bulk
    for k = 1:n
        derivative = 0;
        for i = 1:m
            h = 0;
            for j = 1:n
                h = h + (theta(j,1) * X(i,j));
            end
            derivative = derivative + ((h - y(i,1)) * X(i,k));
        end
        temp_theta(k,1) = temp_theta(k,1) - (alpha / m * derivative);
    end
    theta = temp_theta;
    
    % ============================================================

    % Save the cost J in every iteration    
    J_history(iter) = computeCostMulti(X, y, theta);

end

end
